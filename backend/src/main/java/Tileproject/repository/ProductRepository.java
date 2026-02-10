package Tileproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Tileproject.model.Product;
import Tileproject.dto.ProductStockDTO;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.isActive = 1")
    List<Product> findActiveProducts();

    @Query("""
    SELECT new Tileproject.dto.ProductStockDTO(
        p.productId,
        p.productName,
        p.imageUrl,
        p.pricePerBox,
        p.size,
        p.material,
        c.categoryName,
        COALESCE(i.stockQuantity, 0)
    )
    FROM Product p
    LEFT JOIN Inventory i ON i.product.productId = p.productId
    JOIN p.category c
    WHERE p.isActive = 1
    """)
    List<ProductStockDTO> findActiveProductsWithStock();


    @Query("""
SELECT new Tileproject.dto.ProductStockDTO(
p.productId,
p.productName,
p.imageUrl,
p.pricePerBox,
p.size,
p.material,
c.categoryName,
i.stockQuantity
)
FROM Product p
JOIN p.category c
JOIN Inventory i ON i.product = p
WHERE c.categoryId = :categoryId
AND p.isActive = 1
""")
List<ProductStockDTO> findProductsByCategory(Integer categoryId);

}
