
package Tileproject.service;

import Tileproject.model.*;
import Tileproject.repository.*;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final InventoryRepository inventoryRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            InventoryRepository inventoryRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // ======================================
    // CREATE ORDER FROM CART
    // ======================================
    public Order createOrderFromCart(User user) {

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PAID");
        order.setTotalAmount(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        // List<CartItem> cartItems =
        //         cartItemRepository.findByCart_User_UserId(user.getUserId());

        // BigDecimal total = BigDecimal.ZERO;

        // for (CartItem cartItem : cartItems) {
        //     Product product = cartItem.getProduct();

        //     OrderItem item = new OrderItem();
        //     item.setOrder(savedOrder);
        //     item.setProduct(product);
        //     item.setProductName(product.getProductName());
        //     item.setImageUrl(product.getImageUrl());
        //     item.setPrice(product.getPricePerBox());
        //     item.setQuantity(cartItem.getQuantity());

        //     total = total.add(
        //             product.getPricePerBox()
        //                     .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
        //     );

        //     orderItemRepository.save(item);
        // }

        //savedOrder.setTotalAmount(total);
        orderRepository.save(savedOrder);
        //cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // ======================================
    // COMPLETE ORDER AFTER PAYMENT
    // ======================================
    public void completeOrder(Order order) {

        User user = order.getUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Save selected address
        order.setDeliveryAddress(cart.getSelectedAddress());

        List<CartItem> cartItems =
                cartItemRepository.findByCart_User_UserId(user.getUserId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            // ===== ORDER ITEM =====
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setProductName(product.getProductName());
            item.setImageUrl(product.getImageUrl());
            item.setPrice(product.getPricePerBox());
            item.setQuantity(cartItem.getQuantity());

            orderItemRepository.save(item);

            // ===== INVENTORY DEDUCTION =====
            Inventory inventory = inventoryRepository
                    .findByProduct_ProductId(product.getProductId())
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));

            int remaining = inventory.getStockQuantity() - cartItem.getQuantity();
            if (remaining < 0) {
                throw new RuntimeException("Out of stock for " + product.getProductName());
            }

            inventory.setStockQuantity(remaining);
            inventoryRepository.save(inventory);

            // ===== TOTAL =====
            total = total.add(
                    product.getPricePerBox()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
        }

        order.setOrderStatus("PAID");
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);
        cart.getItems().clear();
        cartRepository.save(cart);

    }
}
