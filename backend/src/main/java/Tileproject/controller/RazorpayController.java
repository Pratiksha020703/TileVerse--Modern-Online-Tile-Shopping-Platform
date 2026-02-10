// package Tileproject.controller;

// import java.util.Map;
// import java.math.BigDecimal;

// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;
//     import Tileproject.service.UserService;

// import Tileproject.model.*;
// import Tileproject.repository.*;
// import Tileproject.service.RazorpayService;
// import Tileproject.service.OrderService;

// @RestController
// @RequestMapping("/api/payment")
// public class RazorpayController {

//    public RazorpayController(
//         RazorpayService razorpayService,
//         PaymentRepository paymentRepository,
//         OrderRepository orderRepository,
//         OrderService orderService,
//         UserService userService
// ) {
//     this.razorpayService = razorpayService;
//     this.paymentRepository = paymentRepository;
//     this.orderRepository = orderRepository;
//     this.orderService = orderService;
//     this.userService = userService;
// }


//     // ===============================
//     // CREATE RAZORPAY ORDER
//     // ===============================
//    @PostMapping("/create")
// public Map<String, Object> create(@RequestBody Map<String, Integer> data) throws Exception {

//     int amount = data.get("amount");

//     // ✅ ALWAYS use DB user
//     User user = userService.getOrCreateUserFromJWT();

//     Order order = new Order();
//     order.setUser(user);
//     order.setOrderStatus("PENDING");
//     order.setTotalAmount(BigDecimal.valueOf(amount));
//     order = orderRepository.save(order);

//     var razorpayOrder = razorpayService.createOrder(amount);

//     return Map.of(
//             "order", Map.of(
//                     "id", razorpayOrder.get("id"),
//                     "amount", razorpayOrder.get("amount"),
//                     "currency", razorpayOrder.get("currency")
//             ),
//             "key", razorpayService.getKeyId(),
//             "internalOrderId", order.getOrderId()
//     );
// }

//     // ===============================
//     // VERIFY & COMPLETE PAYMENT
//     // ===============================
//     // @PostMapping("/verify")
//     // public String verifyAndSave(@RequestBody Map<String, String> data) throws Exception {

//     //     boolean valid = razorpayService.verifyPayment(
//     //             data.get("razorpay_order_id"),
//     //             data.get("razorpay_payment_id"),
//     //             data.get("razorpay_signature")
//     //     );

//     //     if (!valid) {
//     //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment");
//     //     }

//     //     Integer orderId = Integer.parseInt(data.get("orderId"));

//     //     Order order = orderRepository.findById(orderId)
//     //             .orElseThrow(() -> new RuntimeException("Order not found"));

//     //     // 🔥 Convert cart → order_items and mark PAID
//     //     orderService.completeOrder(order);

//     //     Payment payment = new Payment();
//     //     payment.setOrder(order);
//     //     payment.setPaymentMethod("RAZORPAY");
//     //     payment.setPaymentStatus("SUCCESS");
//     //     payment.setTransactionId(data.get("razorpay_payment_id"));

//     //     paymentRepository.save(payment);

//     //     return "OK";
//     // }

// @PostMapping("/verify")
// public String verifyAndSave(@RequestBody Map<String, String> data) throws Exception {

//     String razorpayOrderId = data.get("razorpay_order_id");
//     String razorpayPaymentId = data.get("razorpay_payment_id");
//     String razorpaySignature = data.get("razorpay_signature");
//     Integer internalOrderId = Integer.parseInt(data.get("internalOrderId"));

//     System.out.println("======= RAZORPAY VERIFY =======");
//     System.out.println("Order ID   : " + razorpayOrderId);
//     System.out.println("Payment ID : " + razorpayPaymentId);
//     System.out.println("Signature  : " + razorpaySignature);
//     System.out.println("InternalID : " + internalOrderId);
//     System.out.println("==============================");

//     boolean valid = razorpayService.verifyPayment(
//             razorpayOrderId,
//             razorpayPaymentId,
//             razorpaySignature
//     );

//     System.out.println("Signature valid = " + valid);

//     if (!valid) {
//         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment");
//     }

//     Order order = orderRepository.findById(internalOrderId)
//             .orElseThrow(() -> new RuntimeException("Order not found"));

//     orderService.completeOrder(order);

//     Payment payment = new Payment();
//     payment.setOrder(order);
//     payment.setPaymentMethod("RAZORPAY");
//     payment.setPaymentStatus("SUCCESS");
//     payment.setTransactionId(razorpayPaymentId);

//     paymentRepository.save(payment);

//     return "OK";
// }

// }

package Tileproject.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import Tileproject.model.*;
import Tileproject.repository.*;
import Tileproject.service.OrderService;
import Tileproject.service.RazorpayService;
import Tileproject.service.UserService;

@RestController
@RequestMapping("/api/payment")
public class RazorpayController {

    // 🔥 THESE WERE MISSING
    private final RazorpayService razorpayService;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final UserService userService;

    public RazorpayController(
            RazorpayService razorpayService,
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            OrderService orderService,
            UserService userService
    ) {
        this.razorpayService = razorpayService;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    // ===============================
    // CREATE RAZORPAY ORDER
    // ===============================
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Integer> data) throws Exception {

        int amount = data.get("amount");

        // ✅ Always get DB user from JWT
        User user = userService.getOrCreateUserFromJWT();

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus("PENDING");
        order.setTotalAmount(BigDecimal.valueOf(amount));
        order = orderRepository.save(order);

        var razorpayOrder = razorpayService.createOrder(amount);

        return Map.of(
                "order", Map.of(
                        "id", razorpayOrder.get("id"),
                        "amount", razorpayOrder.get("amount"),
                        "currency", razorpayOrder.get("currency")
                ),
                "key", razorpayService.getKeyId(),
                "internalOrderId", order.getOrderId()
        );
    }

    // ===============================
    // VERIFY & COMPLETE PAYMENT
    // ===============================
//     @PostMapping("/verify")
// public String verifyAndSave(@RequestBody Map<String, String> data) throws Exception {

//         boolean valid = razorpayService.verifyPayment(
//                 data.get("razorpay_order_id"),
//                 data.get("razorpay_payment_id"),
//                 data.get("razorpay_signature")
//         );

//         if (!valid) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment");
//         }

//         Integer orderId = Integer.parseInt(data.get("orderId"));

//         Order order = orderRepository.findById(orderId)
//                 .orElseThrow(() -> new RuntimeException("Order not found"));

//         // 🔥 Convert cart → order_items & mark PAID
//         orderService.completeOrder(order);

//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setPaymentMethod("RAZORPAY");
//         payment.setPaymentStatus("SUCCESS");
//         payment.setTransactionId(data.get("razorpay_payment_id"));

//         paymentRepository.save(payment);

//         return "OK";
//     }

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@PostMapping("/verify")
public Map<String, Object> verifyAndSave(@RequestBody Map<String, String> data) throws Exception {

    boolean valid = razorpayService.verifyPayment(
            data.get("razorpay_order_id"),
            data.get("razorpay_payment_id"),
            data.get("razorpay_signature")
    );

    if (!valid) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment");
    }

    Integer orderId = Integer.parseInt(data.get("orderId"));

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    // 🔥 Convert cart → order_items & mark PAID
    orderService.completeOrder(order);

    Payment payment = new Payment();
    payment.setOrder(order);
    payment.setPaymentMethod("RAZORPAY");
    payment.setPaymentStatus("SUCCESS");
    payment.setTransactionId(data.get("razorpay_payment_id"));

    paymentRepository.save(payment);

    // 🔥 THIS is the missing piece
    return Map.of(
        "status", "success",
        "orderId", order.getOrderId()
    );
}

}

