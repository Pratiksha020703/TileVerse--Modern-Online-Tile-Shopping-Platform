package Tileproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
	public class Wishlist {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer wishlistId;

	    @ManyToOne
	    private User user;

	    @ManyToOne
@JoinColumn(name = "product_id")   // 👈 forces correct FK column
private Product product;


		public Integer getWishlistId() {
			return wishlistId;
		}

		public void setWishlistId(Integer wishlistId) {
			this.wishlistId = wishlistId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
	    
	}

