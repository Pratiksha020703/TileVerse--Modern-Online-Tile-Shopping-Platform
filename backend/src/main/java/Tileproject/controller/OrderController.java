// package Tileproject.controller;

// import Tileproject.model.Order;
// import Tileproject.model.OrderItem;
// import Tileproject.model.User;
// import Tileproject.repository.OrderItemRepository;
// import Tileproject.repository.OrderRepository;
// import Tileproject.service.UserService;
// import org.springframework.security.core.context.SecurityContextHolder;

// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {

//     private final OrderRepository orderRepository;
//     private final OrderItemRepository orderItemRepository;
//     private final UserService userService;

//     public OrderController(OrderRepository orderRepository,
//                            OrderItemRepository orderItemRepository,
//                            UserService userService) {
//         this.orderRepository = orderRepository;
//         this.orderItemRepository = orderItemRepository;
//         this.userService = userService;
//     }

//     // =========================================
//     // 🔹 1. MY ORDERS (Order History Page)
//     // =========================================
//  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
// @GetMapping("/my")
// public List<Map<String, Object>> myOrders() {

//     User user = (User) SecurityContextHolder.getContext()
//             .getAuthentication()
//             .getPrincipal();

//     List<Order> orders =
//             orderRepository.findByUser_UserIdOrderByOrderDateDesc(user.getUserId());

//     List<Map<String, Object>> response = new ArrayList<>();

//     for (Order order : orders) {
//         response.add(Map.of(
//                 "orderId", order.getOrderId(),
//                 "orderDate", order.getOrderDate(),
//                 "orderStatus", order.getOrderStatus(),
//                 "totalAmount", order.getTotalAmount()
//         ));
//     }

//     return response;
// }




//     // =========================================
//     // 🔹 2. ORDER ITEMS (Order Details Page)
//     // =========================================
//     @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
// @GetMapping("/{orderId}/items")
// public List<OrderItem> getOrderItems(@PathVariable Integer orderId) {

//     User user = (User) SecurityContextHolder.getContext()
//             .getAuthentication()
//             .getPrincipal();

//     Order order = orderRepository.findById(orderId)
//             .orElseThrow(() -> new RuntimeException("Order not found"));

//     if (!order.getUser().getUserId().equals(user.getUserId())) {
//         throw new RuntimeException("Unauthorized access");
//     }

//     return orderItemRepository.findByOrder_OrderId(orderId);
// }

// // =========================================
// // 🔹 3. ADMIN – ALL ORDERS DASHBOARD
// // =========================================
// @PreAuthorize("hasRole('ADMIN')")
// @GetMapping("/admin/all")
// public List<Map<String, Object>> getAllOrdersForAdmin() {

//     List<Order> orders = orderRepository.findAll();

//     List<Map<String, Object>> response = new ArrayList<>();

//     for (Order order : orders) {
//         User user = order.getUser();

//         response.add(Map.of(
//                 "orderId", order.getOrderId(),
//                 "orderDate", order.getOrderDate(),
//                 "orderStatus", order.getOrderStatus(),
//                 "totalAmount", order.getTotalAmount(),
//                 "userEmail", user != null ? user.getEmail() : "N/A"
//         ));
//     }

//     return response;
// }


//}

package Tileproject.controller;

import Tileproject.model.Order;
import Tileproject.model.OrderItem;
import Tileproject.model.User;
import Tileproject.repository.OrderItemRepository;
import Tileproject.repository.OrderRepository;
import Tileproject.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    public OrderController(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            UserService userService
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@GetMapping("/{orderId}")
public Order getOrder(@PathVariable Integer orderId) {

    User user = userService.getOrCreateUserFromJWT();

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    // Security check
    if (!order.getUser().getUserId().equals(user.getUserId())) {
        throw new RuntimeException("Unauthorized");
    }

    return order;
}


    // ================================
    // 1️⃣ MY ORDERS (Order History)
    // ================================
    // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    // @GetMapping("/my")
    // public List<Map<String, Object>> myOrders() {

    //     User user = userService.getOrCreateUserFromJWT();


    //     List<Order> orders =
    //             orderRepository.findByUser_UserIdOrderByOrderDateDesc(user.getUserId());

    //     List<Map<String, Object>> response = new ArrayList<>();

    //     for (Order order : orders) {
    //         response.add(Map.of(
    //                 "orderId", order.getOrderId(),
    //                 "orderDate", order.getOrderDate(),
    //                 "orderStatus", order.getOrderStatus(),
    //                 "totalAmount", order.getTotalAmount()
    //         ));
    //     }

    //     return response;
    // }

//     @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
// @GetMapping("/my")
// public List<Map<String, Object>> myOrders() {

//     User user = userService.getOrCreateUserFromJWT();   // 🔥 FIXED

//     List<Order> orders =
//             orderRepository.findByUser_UserIdOrderByOrderDateDesc(user.getUserId());

//     List<Map<String, Object>> response = new ArrayList<>();

//     for (Order order : orders) {
//         response.add(Map.of(
//                 "orderId", order.getOrderId(),
//                 "orderDate", order.getOrderDate(),
//                 "orderStatus", order.getOrderStatus(),
//                 "totalAmount", order.getTotalAmount()
//         ));
//     }

//     return response;
// }

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@GetMapping("/my")
public List<Map<String, Object>> myOrders() {

    User user = userService.getOrCreateUserFromJWT();

    List<Order> orders =
            orderRepository.findByUser_UserIdOrderByOrderDateDesc(user.getUserId());

    List<Map<String, Object>> response = new ArrayList<>();

    for (Order order : orders) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        map.put("orderDate", order.getOrderDate());
        map.put("orderStatus", order.getOrderStatus());
        map.put("totalAmount", order.getTotalAmount());

        response.add(map);
    }

    return response;
}



    // ===================================
    // 2️⃣ ORDER ITEMS (Order Details)
    // ===================================
//     @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
//     @GetMapping("/{orderId}/items")
//     public List<OrderItem> getOrderItems(@PathVariable Integer orderId) {

//         User user  = userService.getOrCreateUserFromJWT();
// ;


//         Order order = orderRepository.findById(orderId)
//                 .orElseThrow(() -> new RuntimeException("Order not found"));

//         // 🔐 Security check: user must own the order
//         if (!order.getUser().getUserId().equals(user.getUserId())) {
//             throw new RuntimeException("Unauthorized access");
//         }

//         return orderItemRepository.findByOrder_OrderId(orderId);
//     }

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@GetMapping("/{orderId}/items")
public List<OrderItem> getOrderItems(@PathVariable Integer orderId) {

    User user = userService.getOrCreateUserFromJWT();   // 🔥 FIXED

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    if (!order.getUser().getUserId().equals(user.getUserId())) {
        throw new RuntimeException("Unauthorized");
    }

    return orderItemRepository.findByOrder_OrderId(orderId);
}


    // ===================================
    // 3️⃣ ADMIN – ALL ORDERS
    // ===================================
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/all")
public List<Map<String, Object>> getAllOrdersForAdmin() {

    List<Order> orders = orderRepository.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    for (Order order : orders) {
        User user = order.getUser();

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        map.put("orderDate", order.getOrderDate());
        map.put("orderStatus", order.getOrderStatus());
        map.put("totalAmount", order.getTotalAmount());
        map.put("userEmail", user != null ? user.getEmail() : null);

        response.add(map);
    }

    return response;
}
}
