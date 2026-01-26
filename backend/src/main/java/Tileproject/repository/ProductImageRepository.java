package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{

	 List<ProductImage> findByProduct_ProductId(Integer productId);	
}
