package com.newsfeed.web;

import com.newsfeed.model.Category;
import com.newsfeed.service.CategoryService;
import com.newsfeed.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService; }

    @GetMapping("/")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PostMapping(value = "/save/{id}")
    public Category update(@RequestBody Category category, @PathVariable long id) {
        category.setId(id);
        return categoryService.save(category);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
