package Tileproject.controller;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import Tileproject.model.Payment;
import Tileproject.service.PaymentService;

	@RestController
	@RequestMapping("/api/payments")
	public class PaymentController {

	    private final PaymentService paymentService;

	    public PaymentController(PaymentService paymentService) {
	        this.paymentService = paymentService;
	    }

	    @PostMapping
	    public Payment makePayment(@RequestBody Payment payment) {
	        return paymentService.makePayment(payment);
	    }

	    @GetMapping("/order/{orderId}")
	    public Payment getPayment(@PathVariable Integer orderId) {
	        return paymentService.getPaymentByOrder(orderId);
	    }
	}


