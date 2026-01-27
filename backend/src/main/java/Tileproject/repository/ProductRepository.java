package Tileproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Tileproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
