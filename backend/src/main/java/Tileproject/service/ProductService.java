
	package Tileproject.service;

	import Tileproject.model.Inventory;
import Tileproject.model.Product;
import Tileproject.repository.InventoryRepository;
import Tileproject.repository.ProductRepository;

import org.springframework.stereotype.Service;
	import java.util.List;

	@Service
	public class ProductService {

	    private final ProductRepository productRepository;
	    private final InventoryRepository inventoryRepository;

	    public ProductService(ProductRepository productRepository,
	                          InventoryRepository inventoryRepository) {
	        this.productRepository = productRepository;
	        this.inventoryRepository = inventoryRepository;
	    }

	    // Add product + inventory
	    public Product addProduct(Product product, Integer stock) {

	        // save product
	        Product savedProduct = productRepository.save(product);

	        // create inventory
	        Inventory inventory = new Inventory();
	        inventory.setProduct(savedProduct);
	        inventory.setStockQuantity(stock);

	        inventoryRepository.save(inventory);

	        return savedProduct;
	    }

	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    public List<Product> getProductsByCategory(Integer categoryId) {
	        return productRepository.findByCategory_CategoryId(categoryId);
	    }
	}


