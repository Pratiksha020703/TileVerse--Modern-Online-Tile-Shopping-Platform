// package Tileproject.service;

// import Tileproject.model.CartItem;
// import Tileproject.model.Order;
// import Tileproject.model.OrderItem;
// import Tileproject.model.User;
// import Tileproject.repository.CartItemRepository;
// import Tileproject.repository.OrderItemRepository;
// import Tileproject.repository.OrderRepository;
// import Tileproject.model.Product;
// import Tileproject.model.Cart;
// import Tileproject.repository.CartRepository;

// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class OrderService {

//     public OrderService(
//         OrderRepository orderRepository,
//         OrderItemRepository orderItemRepository,
//         CartItemRepository cartItemRepository,
//         CartRepository cartRepository
// ) {
//     this.orderRepository = orderRepository;
//     this.orderItemRepository = orderItemRepository;
//     this.cartItemRepository = cartItemRepository;
//     this.cartRepository = cartRepository;
// }


//     // Called AFTER Razorpay success
//     public Order createOrderFromCart(User user) {

//         // 1️⃣ Create Order
//         Order order = new Order();
//         order.setUser(user);
//         order.setOrderDate(LocalDateTime.now());
//         order.setOrderStatus("PAID");
//         order.setTotalAmount(BigDecimal.ZERO);

//         Order savedOrder = orderRepository.save(order);

//         // 2️⃣ Get Cart Items
//         List<CartItem> cartItems =
//                 cartItemRepository.findByCart_User_UserId(user.getUserId());

//         BigDecimal total = BigDecimal.ZERO;

//         // 3️⃣ Move cart → order_item
//         // for (CartItem cartItem : cartItems) {
//         //     OrderItem item = new OrderItem();
//         //     item.setOrder(savedOrder);
//         //     item.setProduct(cartItem.getProduct());
//         //     item.setQuantity(cartItem.getQuantity());
//         //     item.setPrice(cartItem.getProduct().getPricePerBox());

//         //     total = total.add(
//         //             item.getPrice().multiply(
//         //                     BigDecimal.valueOf(item.getQuantity()))
//         //     );

//         //     orderItemRepository.save(item);
//         // }

//         for (CartItem cartItem : cartItems) {

//     Product product = cartItem.getProduct();

//     OrderItem item = new OrderItem();
//     item.setOrder(savedOrder);

//     // 🔥 Keep FK (optional)
//     item.setProduct(product);

//     // 🔥 Snapshot fields (used for View Orders)
//     item.setProductName(product.getProductName());
//     item.setImageUrl(product.getImageUrl());
//     item.setPrice(product.getPricePerBox());
//     item.setQuantity(cartItem.getQuantity());

//     total = total.add(
//             product.getPricePerBox().multiply(
//                     BigDecimal.valueOf(cartItem.getQuantity()))
//     );

//     orderItemRepository.save(item);
// }


//         // 4️⃣ Update order total
//         savedOrder.setTotalAmount(total);
//         orderRepository.save(savedOrder);

//         // 5️⃣ Clear Cart
//         cartItemRepository.deleteAll(cartItems);

//         return savedOrder;
//     }

//     public void completeOrder(Order order) {

//     User user = order.getUser();

//     Cart cart = cartRepository.findByUser(user)
//             .orElseThrow(() -> new RuntimeException("Cart not found"));
    
//             order.setDeliveryAddress(cart.getSelectedAddress());


//     List<CartItem> cartItems =
//             cartItemRepository.findByCart_User_UserId(user.getUserId());

//     if (cartItems.isEmpty()) {
//         throw new RuntimeException("Cart is empty");
//     }

//     BigDecimal total = BigDecimal.ZERO;

//     for (CartItem cartItem : cartItems) {
//         Product product = cartItem.getProduct();

//         OrderItem item = new OrderItem();
//         item.setOrder(order);
//         item.setProduct(product);

//         item.setProductName(product.getProductName());
//         item.setImageUrl(product.getImageUrl());
//         item.setPrice(product.getPricePerBox());
//         item.setQuantity(cartItem.getQuantity());

//         total = total.add(
//                 product.getPricePerBox()
//                         .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
//         );

//         orderItemRepository.save(item);
//     }

//     // ✅ THIS is what you were missing

//     order.setOrderStatus("PAID");
//     order.setOrderDate(LocalDateTime.now());
//     order.setTotalAmount(total);
//     orderRepository.save(order);

//     cartItemRepository.deleteAll(cartItems);
// }


// }
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

        List<CartItem> cartItems =
                cartItemRepository.findByCart_User_UserId(user.getUserId());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(product);
            item.setProductName(product.getProductName());
            item.setImageUrl(product.getImageUrl());
            item.setPrice(product.getPricePerBox());
            item.setQuantity(cartItem.getQuantity());

            total = total.add(
                    product.getPricePerBox()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );

            orderItemRepository.save(item);
        }

        savedOrder.setTotalAmount(total);
        orderRepository.save(savedOrder);
        cartItemRepository.deleteAll(cartItems);

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
    }
}
