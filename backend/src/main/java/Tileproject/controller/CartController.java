package Tileproject.controller;

import Tileproject.model.*;
import Tileproject.repository.*;
import Tileproject.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import Tileproject.repository.AddressRepository;



@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductRepository productRepo;
    private final UserService userService;   // 🔥 THIS WAS MISSING
    private final AddressRepository addressRepository;


    public CartController(
            CartRepository cartRepo,
            CartItemRepository cartItemRepo,
            ProductRepository productRepo,
            UserService userService,           // 🔥 INJECTED HERE
             AddressRepository addressRepository
            ) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.productRepo = productRepo;
        this.userService = userService;
        this.addressRepository = addressRepository;      // 🔥 ASSIGNED HERE
    }


    // 🔥 Get logged-in user from JWT
    private User getUser() {
    return userService.getOrCreateUserFromJWT();
}


    // ===========================
    // ADD TO CART
    // ===========================
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/add/{productId}")
    public Cart addToCart(@PathVariable Integer productId) {

        User user = getUser();

        Cart cart = cartRepo.findByUser(user)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUser(user);
                    return cartRepo.save(c);
                });

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                cartItemRepo.save(item);
                return cart;
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(1);

        cartItemRepo.save(item);
        return cart;
    }

    // ===========================
    // GET CART
    // ===========================
    // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    // @GetMapping
    // public Cart getCart() {

    //     User user = getUser();

    //     return cartRepo.findByUser(user)
    //             .orElseGet(() -> {
    //                 Cart c = new Cart();
    //                 c.setUser(user);
    //                 return cartRepo.save(c);
    //             });
    // }

    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@GetMapping
public Cart getCart() {

    User user = getUser();

    Cart cart = cartRepo.findByUser(user)
            .orElseGet(() -> {
                Cart c = new Cart();
                c.setUser(user);
                return cartRepo.save(c);
            });

    // 🔥 Force Hibernate to reload cart items from DB
    cartRepo.flush();
    return cartRepo.findById(cart.getCartId()).orElseThrow();
}


    // ===========================
    // CART TOTAL
    // ===========================
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/total")
    public Double getTotal() {

        User user = getUser();

        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        return cart.getItems().stream()
                .mapToDouble(i ->
                        i.getProduct().getPricePerBox().doubleValue() * i.getQuantity()
                )
                .sum();
    }

    // ===========================
    // REMOVE ITEM
    // ===========================
    // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    // @DeleteMapping("/remove/item/{itemId}")
    // public void removeCartItem(@PathVariable Integer itemId) {

    //     User user = getUser();
    //     Cart cart = cartRepo.findByUser(user).orElseThrow();

    //     CartItem item = cartItemRepo.findById(itemId).orElseThrow();

    //     if (!item.getCart().getCartId().equals(cart.getCartId())) {
    //         throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your cart");
    //     }

    //     cartItemRepo.delete(item);
    // }

//     @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
// @DeleteMapping("/remove/item/{itemId}")
// public Cart removeCartItem(@PathVariable Integer itemId) {

//     User user = getUser();
//     Cart cart = cartRepo.findByUser(user).orElseThrow();

//     CartItem item = cartItemRepo.findById(itemId).orElseThrow();

//     if (!item.getCart().getCartId().equals(cart.getCartId())) {
//         throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your cart");
//     }

//     cartItemRepo.delete(item);
//     cartRepo.flush();   // 🔥 important

//     return cartRepo.findById(cart.getCartId()).orElseThrow();   // refreshed cart
// }

// @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
// @DeleteMapping("/remove/item/{itemId}")
// public ResponseEntity<?> removeCartItem(@PathVariable Integer itemId) {

//     User user = getUser();

//     CartItem item = cartItemRepo.findById(itemId).orElse(null);

//     // 🔥 If already deleted by admin cascade → just return OK
//     if (item == null) {
//         return ResponseEntity.ok().build();
//     }

//     if (!item.getCart().getUser().getUserId().equals(user.getUserId())) {
//         return ResponseEntity.status(403).build();
//     }

//     cartItemRepo.deleteById(itemId);
//     cartItemRepo.flush();

//     return ResponseEntity.ok().build();
// }

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@DeleteMapping("/remove/item/{itemId}")
public ResponseEntity<?> removeCartItem(@PathVariable Integer itemId) {

    User user = getUser();

    // 🔥 Delete only if this cart item belongs to this user
    cartItemRepo.deleteFromCart(user.getUserId(), itemId);
cartItemRepo.flush();   // 🔥 VERY IMPORTANT


    return ResponseEntity.ok().build();
}

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@PutMapping("/update/{itemId}/{qty}")
public ResponseEntity<?> updateQuantity(
        @PathVariable Integer itemId,
        @PathVariable Integer qty
) {
    User user = getUser();

    CartItem item = cartItemRepo.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item not found"));

    // 🔐 Security: only owner can change
    if (!item.getCart().getUser().getUserId().equals(user.getUserId())) {
        return ResponseEntity.status(403).build();
    }

    if (qty <= 0) {
        cartItemRepo.delete(item);
    } else {
        item.setQuantity(qty);
        cartItemRepo.save(item);
    }

    return ResponseEntity.ok().build();
}

@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
@PostMapping("/select-address/{addressId}")
public void selectAddress(@PathVariable Integer addressId) {

    User user = getUser();

    Cart cart = cartRepo.findByUser(user).orElseThrow();

    Address address = addressRepository.findById(addressId)
            .orElseThrow(() -> new RuntimeException("Address not found"));

    cart.setSelectedAddress(address);
    cartRepo.save(cart);
}


}

