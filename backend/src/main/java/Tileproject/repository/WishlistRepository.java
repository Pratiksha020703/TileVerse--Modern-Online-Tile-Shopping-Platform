package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	
	List<Wishlist> findByUser_UserId(Integer userId);

}
