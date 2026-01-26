package Tileproject.controller;
import org.springframework.web.bind.annotation.*;

import Tileproject.model.Product;
import Tileproject.service.ProductService;

import java.util.List;

	@RestController
	@RequestMapping("/api/products")
	public class ProductController {

	    private final ProductService productService;

	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }

	    // Add product with stock
	    @PostMapping("/{stock}")
	    public Product addProduct(@RequestBody Product product,
	                              @PathVariable Integer stock) {
	        return productService.addProduct(product, stock);
	    }

	    @GetMapping
	    public List<Product> getAllProducts() {
	        return productService.getAllProducts();
	    }

	    @GetMapping("/category/{categoryId}")
	    public List<Product> getByCategory(@PathVariable Integer categoryId) {
	        return productService.getProductsByCategory(categoryId);
	    }
	}


