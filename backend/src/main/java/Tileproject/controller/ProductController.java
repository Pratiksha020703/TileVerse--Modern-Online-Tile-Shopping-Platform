package Tileproject.controller;

import Tileproject.model.Product;
import Tileproject.model.Category;
import Tileproject.model.Brand;
import Tileproject.repository.ProductRepository;
import Tileproject.repository.CategoryRepository;
import Tileproject.repository.BrandRepository;
import Tileproject.repository.CartItemRepository;
import Tileproject.repository.InventoryRepository;
import Tileproject.dto.ProductStockDTO;
import Tileproject.model.Inventory;

import Tileproject.service.ProductService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final InventoryRepository inventoryRepository;   // 🔥 NEW


   public ProductController(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        BrandRepository brandRepository,
        CartItemRepository cartItemRepository,
        ProductService productService,
        InventoryRepository inventoryRepository   // 🔥 add this
) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.brandRepository = brandRepository;
    this.cartItemRepository = cartItemRepository;
    this.productService = productService;
    this.inventoryRepository = inventoryRepository;   // 🔥 add this
}



    // 🔓 PUBLIC – GET ALL PRODUCTS
//     @GetMapping
//     public List<Product> getAllProducts() {
//         return productRepository.findAll();
//     }

@GetMapping
public List<ProductStockDTO> getAllProducts() {
    return productRepository.findActiveProductsWithStock();
}

@GetMapping("/category/{categoryId}")
public List<ProductStockDTO> getProductsByCategory(@PathVariable Integer categoryId) {
    return productRepository.findProductsByCategory(categoryId);
}



// @GetMapping
// public List<ProductStockDTO> getAllProducts() {
//     return productRepository.findProductsWithStock()
//             .stream()
//             .map(obj -> {
//                 Product p = (Product) obj[0];
//                 Integer stock = (Integer) obj[1];

//                 return new ProductStockDTO(
//                         p.getProductId(),
//                         p.getProductName(),
//                         p.getImageUrl(),
//                         p.getPricePerBox(),
//                         p.getSize(),
//                         p.getMaterial(),
//                         //p.getCategory(),
//                         stock == null ? 0 : stock   // 🔥 stock now attached
//                 );
//             }).toList();
// }


    // 🔓 PUBLIC – GET ONE PRODUCT
        // @GetMapping("/{id}")
        // public Product getProductById(@PathVariable Integer id) {
        //      return productRepository.findById(id)
        //      .orElseThrow(() -> new RuntimeException("Product not found"));
        // }

        @GetMapping("/{id}")
public ProductStockDTO getProductById(@PathVariable Integer id) {

    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    Inventory inventory = inventoryRepository
            .findByProduct_ProductId(id)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));

    return new ProductStockDTO(
            product.getProductId(),
            product.getProductName(),
            product.getImageUrl(),
            product.getPricePerBox(),
            product.getSize(),
            product.getMaterial(),
            product.getCategory().getCategoryName(),
            inventory.getStockQuantity()
    );
}



    // 🔐 ADMIN – ADD PRODUCT
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Product addProduct(@RequestBody Product product) {

        Category category = categoryRepository.findById(
                product.getCategory().getCategoryId()
        ).orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(
                product.getBrand().getBrandId()
        ).orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setCategory(category);
        product.setBrand(brand);

        Product saved = productRepository.save(product);

// 🔥 CREATE INVENTORY ROW
Inventory inventory = new Inventory();
inventory.setProduct(saved);
inventory.setStockQuantity(100);   // default stock
inventoryRepository.save(inventory);

return saved;

    }

    // 🔐 ADMIN – UPDATE PRODUCT
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Integer id,
            @RequestBody Product updatedProduct
    ) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(
                updatedProduct.getCategory().getCategoryId()
        ).orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(
                updatedProduct.getBrand().getBrandId()
        ).orElseThrow(() -> new RuntimeException("Brand not found"));

        existing.setProductName(updatedProduct.getProductName());
        existing.setMaterial(updatedProduct.getMaterial());
        existing.setPricePerBox(updatedProduct.getPricePerBox());
        existing.setSize(updatedProduct.getSize());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setCategory(category);
        existing.setBrand(brand);

        return productRepository.save(existing);
    }

//     // 🔐 ADMIN – DELETE PRODUCT
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/admin/{id}")
public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
    productService.softDeleteProduct(id);
    return ResponseEntity.ok("Product deleted");
}


// @PreAuthorize("hasRole('ADMIN')")
// @DeleteMapping("/admin/product/{id}")
// public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

//     // remove product from all carts
//     cartItemRepository.deleteByProductId(id);

//     // soft delete product
//     Product p = productRepository.findById(id).orElseThrow();
//     p.setIsActive(0);
//     productRepository.save(p);

//     return ResponseEntity.ok("Product deleted");
// }

// @PreAuthorize("hasRole('ADMIN')")
// @DeleteMapping("/admin/{id}")
// @Transactional
// public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

//     // 1️⃣ FORCE delete from cart first
//     cartItemRepository.deleteByProductId(id);
//     cartItemRepository.flush();   // 🔥 force SQL execution

//     // 2️⃣ Soft delete product
//     Product p = productRepository.findById(id)
//             .orElseThrow(() -> new RuntimeException("Product not found"));

//     p.setIsActive(0);
//     productRepository.save(p);

//     return ResponseEntity.ok("Product deleted");
// }


}
