package Tileproject.controller;
import org.springframework.web.bind.annotation.*;
import Tileproject.model.Category;

import Tileproject.service.CategoryService;

import java.util.List;

	@RestController
	@RequestMapping("/api/categories")
	public class CategoryController {

	    private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }

	    @PostMapping
	    public Category addCategory(@RequestBody Category category) {
	        return categoryService.addCategory(category);
	    }

	    @GetMapping
	    public List<Category> getAll() {
	        return categoryService.getAllCategories();
	    }
	}


