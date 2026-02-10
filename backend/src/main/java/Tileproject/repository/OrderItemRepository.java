package Tileproject.repository;

import Tileproject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder_OrderId(Integer orderId);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItem o WHERE o.product.productId = :productId")
    void deleteByProductId(Integer productId);
}
