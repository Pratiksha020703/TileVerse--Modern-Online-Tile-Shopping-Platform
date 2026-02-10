// package Tileproject.model;

// import java.math.BigDecimal;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import Tileproject.model.Category;


// @Entity
// public class Product {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer productId;

//     @ManyToOne
//     private Category category;

//     @ManyToOne
//     private Brand brand;

//     private String productName;
//     private BigDecimal pricePerBox;
//     private BigDecimal coverageSqft;
//     private String material;
//     private String finish;
//     private String size;

//     // ===== GETTERS & SETTERS =====

//     public Integer getProductId() {
//         return productId;
//     }

//     public void setProductId(Integer productId) {
//         this.productId = productId;
//     }

//     public Category getCategory() {
//         return category;
//     }

//     public void setCategory(Category category) {
//         this.category = category;
//     }

//     public Brand getBrand() {
//         return brand;
//     }

//     public void setBrand(Brand brand) {
//         this.brand = brand;
//     }

//     public String getProductName() {
//         return productName;
//     }

//     public void setProductName(String productName) {
//         this.productName = productName;
//     }

//     public BigDecimal getPricePerBox() {
//         return pricePerBox;
//     }

//     public void setPricePerBox(BigDecimal pricePerBox) {
//         this.pricePerBox = pricePerBox;
//     }

//     public BigDecimal getCoverageSqft() {
//         return coverageSqft;
//     }

//     public void setCoverageSqft(BigDecimal coverageSqft) {
//         this.coverageSqft = coverageSqft;
//     }

//     public String getMaterial() {
//         return material;
//     }

//     public void setMaterial(String material) {
//         this.material = material;
//     }

//     public String getFinish() {
//         return finish;
//     }

//     public void setFinish(String finish) {
//         this.finish = finish;
//     }

//     public String getSize() {
//         return size;
//     }

//     public void setSize(String size) {
//         this.size = size;
//     }
// }
package Tileproject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false)
    private String productName;

    @Column(name="stock_quantity")
private int stockQuantity;


    @Column(name = "is_active")
private Integer isActive = 1;


    // ✅ FIXED FIELD
    @Column
    private String material;

    @Column(nullable = false)
    private BigDecimal pricePerBox;

    private String size;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


@JsonIgnore
@OneToMany(mappedBy = "product")
private List<OrderItem> orderItems;


    /* ===== GETTERS & SETTERS ===== */

    public Integer getIsActive() {
    return isActive;
}

public void setIsActive(Integer isActive) {
    this.isActive = isActive;
}


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // ✅ FIXED GETTER / SETTER
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPricePerBox() {
        return pricePerBox;
    }

    public void setPricePerBox(BigDecimal pricePerBox) {
        this.pricePerBox = pricePerBox;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
