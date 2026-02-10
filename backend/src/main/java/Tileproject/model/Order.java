
package Tileproject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;   // ✅ ADD THIS
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer orderId;

    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items;

     @ManyToOne
@JoinColumn(name="user_id")
private User user;

    // @Column(nullable = false)
    // private String userEmail;   // ✅ ADD THIS

    private LocalDateTime orderDate;
    private String orderStatus;
    private BigDecimal totalAmount;

    @ManyToOne
@JoinColumn(name = "address_id")
@JsonIgnoreProperties({"user"})
private Address deliveryAddress;





    // ===== GETTERS & SETTERS =====
   

public User getUser() { return user; }
public void setUser(User user) { this.user = user; }

public List<OrderItem> getItems() {
    return items;
}
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }



}


