package com.becoder.CatagoryService;

import com.becoder.CatagoryDto.CategoryDto;
import com.becoder.CatagoryDto.CategoryResponse;
import com.becoder.Entity.Category;

import java.util.List;

public interface CategoryService {

    public  Boolean saveCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategory();

     public List<CategoryResponse> getActiveCategory();


    public  CategoryDto getCategoryById(Integer id) throws Exception;

    public Boolean DeleteCategoryById(Integer id);
}
