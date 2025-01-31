package com.becoder.Controller;

import com.becoder.CatagoryDto.CategoryDto;
import com.becoder.CatagoryDto.CategoryResponse;
import com.becoder.CatagoryService.CategoryService;
import com.becoder.Entity.Category;
import com.becoder.Exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) {
            return new ResponseEntity<>("saved success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("  Not saved ", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        String num=null;
        num.toUpperCase();
        List<CategoryDto> allCategory = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }

    }

    @GetMapping("/active-category")
    public HttpEntity<List<CategoryResponse>> getActiveCategory() {
        List<CategoryResponse> allCategory = categoryService.getActiveCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws  Exception {

        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }


            @DeleteMapping("/{id}")
            public ResponseEntity<String> deleteCategoryById (@PathVariable Integer id){
                Boolean deleted = categoryService.DeleteCategoryById(id);  // Expects Boolean

                if (deleted) {
                    return new ResponseEntity<>("Category deleted successfully: " + id, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Category not found or deletion failed", HttpStatus.NOT_FOUND);
                }
            }


        }

