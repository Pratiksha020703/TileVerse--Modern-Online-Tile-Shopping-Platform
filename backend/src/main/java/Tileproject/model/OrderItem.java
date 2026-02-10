
// package Tileproject.model;

// import jakarta.persistence.*;
// import java.math.BigDecimal;
// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



// @Entity
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer orderItemId;

//     @JsonBackReference
//     @ManyToOne
//     @JoinColumn(name = "order_id")
//     private Order order;


//     // @ManyToOne
//     // @JoinColumn(name = "product_id")
//     // @JsonIgnoreProperties({"orderItems", "cartItems"})
//     // private Product product;

    

//     private Integer quantity;
//     private BigDecimal price;

//     // ===== GETTERS & SETTERS =====

//     public Integer getOrderItemId() {
//         return orderItemId;
//     }

//     public void setOrderItemId(Integer orderItemId) {
//         this.orderItemId = orderItemId;
//     }

//     public Order getOrder() {
//         return order;
//     }

//     public void setOrder(Order order) {
//         this.order = order;
//     }

//     public Product getProduct() {
//         return product;
//     }

//     public void setProduct(Product product) {
//         this.product = product;
//     }

//     public Integer getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(Integer quantity) {
//         this.quantity = quantity;
//     }

//     public BigDecimal getPrice() {
//         return price;
//     }

//     public void setPrice(BigDecimal price) {
//         this.price = price;
//     }
// }
package Tileproject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // 🔥 We do NOT expose product relationship to frontend
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // 🔥 Snapshot fields used for UI
    private String productName;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;

    // ===== GETTERS & SETTERS =====

    public Integer getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Integer orderItemId) { this.orderItemId = orderItemId; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
