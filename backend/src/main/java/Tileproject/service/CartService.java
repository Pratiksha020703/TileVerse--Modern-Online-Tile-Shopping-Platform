package Tileproject.service;

import Tileproject.model.Cart;
import Tileproject.model.CartItem;
import Tileproject.model.Product;
import Tileproject.model.User;
import Tileproject.repository.CartItemRepository;
import Tileproject.repository.CartRepository;
import Tileproject.repository.ProductRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // 🔥 Get or create cart for logged-in user
    public Cart getOrCreateCart() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    // 🔥 Add product to cart
    public CartItem addToCart(Integer productId, Integer quantity) {

        Cart cart = getOrCreateCart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }
}
