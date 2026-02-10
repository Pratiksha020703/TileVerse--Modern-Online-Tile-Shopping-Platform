package Tileproject.dto;

import java.math.BigDecimal;

public class ProductStockDTO {

    private Integer productId;
    private String productName;
    private String imageUrl;
    private BigDecimal pricePerBox;
    private String size;
    private String material;
    private String categoryName;
    private Integer stock;
    

    public ProductStockDTO(
            Integer productId,
            String productName,
            String imageUrl,
            BigDecimal pricePerBox,
            String size,
            String material,
            String categoryName,
            Integer stock
    ) {
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.pricePerBox = pricePerBox;
        this.size = size;
        this.material = material;
        this.categoryName = categoryName;
        this.stock = stock;
    }

    // getters
    public Integer getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getImageUrl() { return imageUrl; }
    public BigDecimal getPricePerBox() { return pricePerBox; }
    public String getSize() { return size; }
    public String getMaterial() { return material; }
    public String getCategoryName() { return categoryName; }
    public Integer getStock() { return stock; }
}
