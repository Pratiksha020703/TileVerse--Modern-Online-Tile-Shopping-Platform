package Tileproject.controller;
import Tileproject.repository.CategoryRepository;
import Tileproject.model.Category;
import Tileproject.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Category add(@RequestBody Category category) {
        return service.save(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return service.findAll();
    }
}
