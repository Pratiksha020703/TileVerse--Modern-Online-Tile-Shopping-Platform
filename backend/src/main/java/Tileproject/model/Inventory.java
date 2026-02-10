// package Tileproject.model;

// import jakarta.persistence.*;

// @Entity
// public class Inventory {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer inventoryId;

//     @OneToOne
//     @OneToOne
//     @JoinColumn(name = "product_id", unique = true)
//     private Product product;

//     private Integer stockQuantity;

//     // ===== GETTERS & SETTERS =====

//     public Integer getInventoryId() {
//         return inventoryId;
//     }

//     public void setInventoryId(Integer inventoryId) {
//         this.inventoryId = inventoryId;
//     }

//     public Product getProduct() {
//         return product;
//     }

//     public void setProduct(Product product) {
//         this.product = product;
//     }

//     public Integer getStockQuantity() {
//         return stockQuantity;
//     }

//     public void setStockQuantity(Integer stockQuantity) {
//         this.stockQuantity = stockQuantity;
//     }
// }



package Tileproject.model;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    // getters & setters



    // ===== Getters & Setters =====
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
