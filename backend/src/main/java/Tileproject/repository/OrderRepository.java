package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUser_UserId(Integer userId);
}
