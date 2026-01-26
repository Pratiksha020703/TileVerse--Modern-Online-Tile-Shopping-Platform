package Tileproject.controller;
import Tileproject.model.CartItem;
import Tileproject.model.Order;
import Tileproject.service.OrderService;

import org.springframework.web.bind.annotation.*;

	import java.util.List;

	@RestController
	@RequestMapping("/api/orders")
	public class OrderController {

	    private final OrderService orderService;

	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }


	    @PostMapping("/place/{userId}")
	    public Order placeOrder(@PathVariable Integer userId,
	                            @RequestBody List<CartItem> cartItems) {
	        return orderService.placeOrder(userId, cartItems);
	    }
	}


