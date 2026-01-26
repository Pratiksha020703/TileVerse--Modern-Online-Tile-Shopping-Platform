package Tileproject.service;

import Tileproject.model.CartItem;
import Tileproject.model.Order;
import Tileproject.model.OrderItem;
import Tileproject.repository.OrderItemRepository;
import Tileproject.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Place order from cart
    public Order placeOrder(Integer userId, List<CartItem> cartItems) {

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");

        BigDecimal total = BigDecimal.ZERO;

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPricePerBox());

            total = total.add(
                    item.getPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity()))
            );

            orderItemRepository.save(item);
        }

        savedOrder.setTotalAmount(total);
        return orderRepository.save(savedOrder);
    }
}

