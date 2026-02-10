package Tileproject.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import Tileproject.model.Review;
import Tileproject.service.ReviewService;

import java.util.List;

	@RestController
	@RequestMapping("/api/reviews")
	public class ReviewController {

	    private final ReviewService reviewService;

	    public ReviewController(ReviewService reviewService) {
	        this.reviewService = reviewService;
	    }

	    @PostMapping
	    public Review addReview(@RequestBody Review review) {
	        return reviewService.addReview(review);
	    }

	    @GetMapping("/product/{productId}")
	    public List<Review> getReviews(@PathVariable Integer productId) {
	        return reviewService.getReviewsByProduct(productId);
	    }
	}


