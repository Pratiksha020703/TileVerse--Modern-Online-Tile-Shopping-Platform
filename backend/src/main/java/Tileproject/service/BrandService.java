package Tileproject.service;
	import org.springframework.stereotype.Service;

import Tileproject.model.Brand;
import Tileproject.repository.BrandRepository;

import java.util.List;

	@Service
	public class BrandService {

	    private final BrandRepository brandRepository;

	    public BrandService(BrandRepository brandRepository) {
	        this.brandRepository = brandRepository;
	    }

	    public Brand addBrand(Brand brand) {
	        return brandRepository.save(brand);
	    }

	    public List<Brand> getAllBrands() {
	        return brandRepository.findAll();
	    }
	}


