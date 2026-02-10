package Tileproject.controller;

import Tileproject.model.*;
import Tileproject.repository.*;
import Tileproject.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistRepository wishlistRepo;
    private final ProductRepository productRepo;
    private final UserService userService;

    public WishlistController(WishlistRepository wishlistRepo,
                              ProductRepository productRepo,
                              UserService userService) {
        this.wishlistRepo = wishlistRepo;
        this.productRepo = productRepo;
        this.userService = userService;
    }

    // ❤️ Add to wishlist
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/add/{productId}")
    public void add(@PathVariable Integer productId) {
        User user = userService.getOrCreateUserFromJWT();

        // prevent duplicate
        for (Wishlist w : wishlistRepo.findByUser_UserId(user.getUserId())) {
            if (w.getProduct().getProductId().equals(productId)) return;
        }

        Product product = productRepo.findById(productId).orElseThrow();

        Wishlist w = new Wishlist();
        w.setUser(user);
        w.setProduct(product);

        wishlistRepo.save(w);
    }

    // 📄 Get wishlist
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping
    public List<Wishlist> get() {
        User user = userService.getOrCreateUserFromJWT();
        return wishlistRepo.findByUser_UserId(user.getUserId());
    }

    // ❌ Remove from wishlist
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @DeleteMapping("/remove/{productId}")
    public void remove(@PathVariable Integer productId) {
        User user = userService.getOrCreateUserFromJWT();

        for (Wishlist w : wishlistRepo.findByUser_UserId(user.getUserId())) {
            if (w.getProduct().getProductId().equals(productId)) {
                wishlistRepo.delete(w);
            }
        }
    }
}
