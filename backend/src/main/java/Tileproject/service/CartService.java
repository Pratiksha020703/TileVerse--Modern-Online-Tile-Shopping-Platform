package Tileproject.service;

import Tileproject.model.Cart;
import Tileproject.model.CartItem;
import Tileproject.model.Product;
import Tileproject.model.User;
import Tileproject.repository.CartItemRepository;
import Tileproject.repository.CartRepository;
import Tileproject.repository.ProductRepository;
import Tileproject.repository.InventoryRepository;
import Tileproject.model.Inventory;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;


    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            InventoryRepository inventoryRepository

    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
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
    // public CartItem addToCart(Integer productId, Integer quantity) {

    //     Cart cart = getOrCreateCart();

    //     Product product = productRepository.findById(productId)
    //             .orElseThrow(() -> new RuntimeException("Product not found"));

    //     CartItem item = new CartItem();
    //     item.setCart(cart);
    //     item.setProduct(product);
    //     item.setQuantity(quantity);

    //     return cartItemRepository.save(item);
    // }

    public CartItem addToCart(Integer productId, Integer quantity) {

    Cart cart = getOrCreateCart();

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    Inventory inventory = inventoryRepository
            .findByProduct_ProductId(productId)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));

    if (inventory.getStockQuantity() <= 0) {
        throw new RuntimeException("Product is out of stock");
    }

    CartItem item = new CartItem();
    item.setCart(cart);
    item.setProduct(product);
    item.setQuantity(quantity);

    return cartItemRepository.save(item);
}

}
