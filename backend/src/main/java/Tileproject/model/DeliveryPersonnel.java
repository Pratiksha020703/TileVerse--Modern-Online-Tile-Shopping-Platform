package Tileproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
	public class DeliveryPersonnel {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer deliveryPersonId;

	    @OneToOne
	    private User user;

	    private String vehicleNumber;
	    private String contactNumber;
		public Integer getDeliveryPersonId() {
			return deliveryPersonId;
		}
		public void setDeliveryPersonId(Integer deliveryPersonId) {
			this.deliveryPersonId = deliveryPersonId;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public String getVehicleNumber() {
			return vehicleNumber;
		}
		public void setVehicleNumber(String vehicleNumber) {
			this.vehicleNumber = vehicleNumber;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
	    
	}


