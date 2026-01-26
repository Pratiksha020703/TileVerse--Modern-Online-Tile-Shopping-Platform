package Tileproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

	Optional<Inventory> findByProduct_ProductId(Integer productId);
}
