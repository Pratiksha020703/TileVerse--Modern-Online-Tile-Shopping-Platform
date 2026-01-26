package Tileproject.service;



	import org.springframework.stereotype.Service;

import Tileproject.model.ProductImage;
import Tileproject.repository.ProductImageRepository;

import java.util.List;

	@Service
	public class ProductImageService {

	    private final ProductImageRepository productImageRepository;

	    public ProductImageService(ProductImageRepository productImageRepository) {
	        this.productImageRepository = productImageRepository;
	    }

	    public ProductImage addImage(ProductImage image) {
	        return productImageRepository.save(image);
	    }

	    public List<ProductImage> getImagesByProduct(Integer productId) {
	        return productImageRepository.findByProduct_ProductId(productId);
	    }
	}


