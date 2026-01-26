package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByProduct_ProductId(Integer productId);
}
