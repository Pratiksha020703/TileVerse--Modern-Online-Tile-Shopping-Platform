package Tileproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import Tileproject.model.Product;
import Tileproject.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ CREATE PRODUCT (THIS WAS MISSING)
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
