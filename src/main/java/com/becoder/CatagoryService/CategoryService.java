package com.becoder.CatagoryService;

import com.becoder.Entity.Category;

import java.util.List;

public interface CategoryService {

    public  Boolean saveCategory(Category category);

    public List<Category> getAllCategory();
}
