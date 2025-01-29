package com.becoder.Controller;

import com.becoder.CatagoryService.CategoryService;
import com.becoder.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save-category")
    public ResponseEntity<?>saveCategory(@RequestBody Category category) {
        Boolean saveCategory = categoryService.saveCategory(category);
        if (saveCategory) {
            return new ResponseEntity<>("saved success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("  Not saved ", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/category")
    public ResponseEntity<?>getAllCategory() {
        List<Category> allCategory = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        }else {
            return  new ResponseEntity<>(allCategory,HttpStatus.OK);
        }

    }


        }

