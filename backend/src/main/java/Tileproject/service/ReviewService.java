package Tileproject.service;



	import org.springframework.stereotype.Service;

import Tileproject.model.Review;
import Tileproject.repository.ReviewRepository;

import java.util.List;

	@Service
	public class ReviewService {

	    private final ReviewRepository reviewRepository;

	    public ReviewService(ReviewRepository reviewRepository) {
	        this.reviewRepository = reviewRepository;
	    }

	    public Review addReview(Review review) {
	        return reviewRepository.save(review);
	    }

	    public List<Review> getReviewsByProduct(Integer productId) {
	        return reviewRepository.findByProduct_ProductId(productId);
	    }
	}


