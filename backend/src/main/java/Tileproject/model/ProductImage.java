package Tileproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
	public class ProductImage {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer imageId;

	    @ManyToOne
@JoinColumn(name = "product_id")   // 👈 forces correct FK column
private Product product;


	    private String imageUrl;

		public Integer getImageId() {
			return imageId;
		}

		public void setImageId(Integer imageId) {
			this.imageId = imageId;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	    
	}

