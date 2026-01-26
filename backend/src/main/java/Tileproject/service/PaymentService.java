package Tileproject.service;



	import org.springframework.stereotype.Service;

import Tileproject.model.Payment;
import Tileproject.repository.PaymentRepository;

	@Service
	public class PaymentService {

	    private final PaymentRepository paymentRepository;

	    public PaymentService(PaymentRepository paymentRepository) {
	        this.paymentRepository = paymentRepository;
	    }

	    public Payment makePayment(Payment payment) {
	        payment.setPaymentStatus("SUCCESS");
	        return paymentRepository.save(payment);
	    }

	    public Payment getPaymentByOrder(Integer orderId) {
	        return paymentRepository.findByOrder_OrderId(orderId).orElse(null);
	    }
	}


