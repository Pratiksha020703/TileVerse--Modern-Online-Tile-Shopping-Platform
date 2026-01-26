package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.DeliveryAssignment;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Integer>{

	List<DeliveryAssignment> findByDeliveryPerson_DeliveryPersonId(Integer deliveryPersonId);

    List<DeliveryAssignment> findByOrder_OrderId(Integer orderId);
}
