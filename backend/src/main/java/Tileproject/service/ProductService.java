package Tileproject.service;

import Tileproject.model.Product;
import Tileproject.repository.CartItemRepository;
import Tileproject.repository.OrderItemRepository;
import Tileproject.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // ===== Get all products =====
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ===== Save product =====
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // ===== Delete product safely =====
    // @Transactional
    // public void deleteProduct(Integer id) {
    //     // Remove product from all carts
    //     cartItemRepository.deleteByProductId(id);

    //     // Remove product from all orders
    //     orderItemRepository.deleteByProductId(id);

    //     // Finally delete the product
    //     // productRepository.deleteById(id);
    // }

// @Transactional
// public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

//     cartItemRepository.deleteByProductId(id);
//     cartItemRepository.flush();

//     orderItemRepository.deleteByProductId(id);
//     orderItemRepository.flush();

//     Product p = productRepository.findById(id).orElseThrow();
//     p.setIsActive(0);
//     productRepository.save(p);

//     return ResponseEntity.ok("Product deleted");
// }

 @Transactional
    public void softDeleteProduct(Integer id) {
        // 1. Remove from carts
        cartItemRepository.deleteByProductId(id);


        // 2. Soft delete product
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setIsActive(0);
        productRepository.save(p);
    }

}
