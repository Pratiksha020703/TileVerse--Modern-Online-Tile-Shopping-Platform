package Tileproject.service;


	import org.springframework.stereotype.Service;

import Tileproject.model.Wishlist;
import Tileproject.repository.WishlistRepository;

import java.util.List;

	@Service
	public class WishlistService {

	    private final WishlistRepository wishlistRepository;

	    public WishlistService(WishlistRepository wishlistRepository) {
	        this.wishlistRepository = wishlistRepository;
	    }

	    public Wishlist addToWishlist(Wishlist wishlist) {
	        return wishlistRepository.save(wishlist);
	    }

	    public List<Wishlist> getWishlistByUser(Integer userId) {
	        return wishlistRepository.findByUser_UserId(userId);
	    }
	}


