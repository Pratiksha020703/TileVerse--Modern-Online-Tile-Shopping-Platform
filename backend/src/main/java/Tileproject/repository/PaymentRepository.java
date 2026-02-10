package Tileproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Tileproject.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrder_OrderId(Integer orderId);
}
