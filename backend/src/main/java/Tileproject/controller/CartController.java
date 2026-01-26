package Tileproject.controller;
import org.springframework.web.bind.annotation.*;
import Tileproject.model.CartItem;
import Tileproject.service.CartService;

	@RestController
	@RequestMapping("/api/cart")
	public class CartController {

	    private final CartService cartService;

	    public CartController(CartService cartService) {
	        this.cartService = cartService;
	    }

	    @PostMapping("/{cartId}/add/{productId}/{qty}")
	    public CartItem addToCart(@PathVariable Integer cartId,
	                              @PathVariable Integer productId,
	                              @PathVariable Integer qty) {
	        return cartService.addToCart(cartId, productId, qty);
	    }
	}


