package Tileproject.service;

import Tileproject.model.Cart;
import Tileproject.model.CartItem;
import Tileproject.model.Product;
import Tileproject.repository.CartItemRepository;
import Tileproject.repository.CartRepository;
import Tileproject.repository.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }


    // Add product to cart
    public CartItem addToCart(Integer cartId, Integer productId, Integer quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }
}



// import Tileproject.model.Product;
// import Tileproject.repository.ProductRepository;
// import Tileproject.repository.UserRepository;
// import Tileproject.repository.CartRepository;
// import Tileproject.repository.CartItemRepository;


// @Service
// public class CartService {

//     private final CartRepository cartRepository;
//     private final CartItemRepository cartItemRepository;
//     private final UserRepository userRepository;
//     private final ProductRepository productRepository;

//     public CartService(CartRepository cartRepository,
//                        CartItemRepository cartItemRepository,
//                        UserRepository userRepository,
//                        ProductRepository productRepository) {
//         this.cartRepository = cartRepository;
//         this.cartItemRepository = cartItemRepository;
//         this.userRepository = userRepository;
//         this.productRepository = productRepository;
//     }

//     public void addToCart(String email, Integer productId, Integer quantity) {

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Cart cart = cartRepository.findByUser(user)
//                 .orElseGet(() -> {
//                     Cart c = new Cart();
//                     c.setUser(user);
//                     return cartRepository.save(c);
//                 });

//         Product product = productRepository.findById(productId)
//                 .orElseThrow(() -> new RuntimeException("Product not found"));

//         CartItem item = new CartItem();
//         item.setCart(cart);
//         item.setProduct(product);
//         item.setQuantity(quantity);

//         cartItemRepository.save(item);
//     }

//     // ✅ USED BY ORDER SERVICE
//     public List<CartItem> getCartItemsByEmail(String email) {
//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Cart cart = cartRepository.findByUser(user)
//                 .orElseThrow(() -> new RuntimeException("Cart not found"));

//         return cartItemRepository.findByCart_CartId(cart.getCartId());
//     }
// }


