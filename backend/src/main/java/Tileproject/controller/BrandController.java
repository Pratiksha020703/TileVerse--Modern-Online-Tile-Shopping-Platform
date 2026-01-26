package Tileproject.controller;
import org.springframework.web.bind.annotation.*;

import Tileproject.model.Brand;
import Tileproject.service.BrandService;

import java.util.List;

	@RestController
	@RequestMapping("/api/brands")
	public class BrandController {

	    private final BrandService brandService;

	    public BrandController(BrandService brandService) {
	        this.brandService = brandService;
	    }

	    @PostMapping
	    public Brand addBrand(@RequestBody Brand brand) {
	        return brandService.addBrand(brand);
	    }

	    @GetMapping
	    public List<Brand> getAllBrands() {
	        return brandService.getAllBrands();
	    }
	}


