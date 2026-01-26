package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	 List<Product> findByCategory_CategoryId(Integer categoryId);

	    List<Product> findByBrand_BrandId(Integer brandId);	
}
