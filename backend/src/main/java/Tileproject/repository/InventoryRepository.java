// package Tileproject.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import Tileproject.model.Inventory;

// public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

// 	Optional<Inventory> findByProduct_ProductId(Integer productId);
// }

package Tileproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import Tileproject.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // 🔥 Find stock for a product
    Optional<Inventory> findByProduct_ProductId(Integer productId);

    // 🔥 Find low-stock products for admin dashboard
    List<Inventory> findByStockQuantityLessThan(Integer stock);

    
}


