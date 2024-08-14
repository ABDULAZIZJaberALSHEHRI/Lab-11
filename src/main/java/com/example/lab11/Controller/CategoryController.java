package com.example.lab11.Controller;

import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get-categories")
    public ResponseEntity getCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add-category")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category added successfully");
    }

    @PutMapping("/update-category/{categoryid}")
    public ResponseEntity updateCategory(@PathVariable int categoryid,@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(categoryid,category);
        return ResponseEntity.status(200).body("Category updated successfully");
    }

    @DeleteMapping("/delete-category/{categoryid}")
    public ResponseEntity deleteCategory(@PathVariable int categoryid) {
        categoryService.deleteCategory(categoryid);
        return ResponseEntity.status(200).body("Category deleted successfully");
    }

    @GetMapping("/get-category-by-name/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(categoryService.searchCategoryByName(name));
    }

}
