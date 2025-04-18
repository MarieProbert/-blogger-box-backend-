package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import com.dauphine.blogger.services.CategoryService;


@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService service;
    private final CategoryService categoryService;

    public CategoryController(CategoryService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> retrieveAllCategories(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getByCategoryName(name);
        return categories;
    }

    @GetMapping("/{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody String name) {
        return service.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        return service.update(id, name);
    }

    @DeleteMapping("/{id}")
    public UUID deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
        return id;
    }
}
