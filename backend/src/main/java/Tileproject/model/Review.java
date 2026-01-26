package Tileproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
	public class Review {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer reviewId;

	    @ManyToOne
	    private Product product;

	    @ManyToOne
	    private User user;

	    private Integer rating;
	    private String comment;
		public Integer getReviewId() {
			return reviewId;
		}
		public void setReviewId(Integer reviewId) {
			this.reviewId = reviewId;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Integer getRating() {
			return rating;
		}
		public void setRating(Integer rating) {
			this.rating = rating;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
	    
	}

