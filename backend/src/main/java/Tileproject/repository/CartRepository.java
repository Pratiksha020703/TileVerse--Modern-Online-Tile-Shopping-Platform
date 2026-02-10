package Tileproject.repository;

import Tileproject.model.Cart;
import Tileproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
}
