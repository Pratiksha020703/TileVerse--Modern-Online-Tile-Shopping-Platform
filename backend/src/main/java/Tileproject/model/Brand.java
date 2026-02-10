package Tileproject.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    private String brandName;


		public Integer getBrandId() {
			return brandId;
		}

		public void setBrandId(Integer brandId) {
			this.brandId = brandId;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
	    
	}

