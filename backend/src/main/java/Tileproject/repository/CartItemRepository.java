package Tileproject.repository;

import Tileproject.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    //Delete cart items when product is removed
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.product.productId = :productId")
    void deleteByProductId(Integer productId);
    

    // 🔥 Used for checkout → move cart to order_item
    List<CartItem> findByCart_User_UserId(Integer userId);

//     @Modifying
// @Transactional
// @Query("DELETE FROM CartItem c WHERE c.cart.cartId = :cartId AND c.product.productId = :productId")
// void deleteFromCart(Integer cartId, Integer productId);

@Modifying
@Transactional
@Query("""
DELETE FROM CartItem c 
WHERE c.cart.user.id = :userId 
AND c.cartItemId = :itemId
""")
void deleteFromCart(Integer userId, Integer itemId);

}
