package Tileproject.controller;

import Tileproject.model.Brand;
import Tileproject.repository.BrandRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // 🔓 PUBLIC
    @GetMapping
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // 🔐 ADMIN ONLY
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Brand addBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }
}
