package Tileproject.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByCart_CartId(Integer cartId);
}
