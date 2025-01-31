package com.becoder.CatagoryService.Impl;


import com.becoder.CatagoryDto.CategoryDto;
import com.becoder.CatagoryDto.CategoryResponse;
import com.becoder.CatagoryService.CategoryService;
import com.becoder.Entity.Category;
import com.becoder.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

//        Category category=new Category();
//        category.setName(categoryDto.getName());
//        category.setDescription(categoryDto.getDescription());
//        category.setIsActive(categoryDto.getIsActive());

        Category category=mapper.map(categoryDto, Category.class);
        if (ObjectUtils.isEmpty(category.getId())){
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
        }else {
            updateCategory(category);
        }
//////////updated category paii heichii..
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedOn(new Date());
        Category save = categoryRepository.save(category);
        if(ObjectUtils.isEmpty(save)){
           return false;
        }else
        return true;
    }
//////////updared category ra method
    private void updateCategory(Category category) {
        Optional<Category> findbyid = categoryRepository.findById(category.getId());
      if (findbyid.isPresent()){
          Category existcategory = findbyid.get();
          category.setCreatedBy(existcategory.getCreatedBy());
          category.setCreatedOn(existcategory.getCreatedOn());
          category.setIsDeleted(existcategory.getIsDeleted());
          category.setUpdatedBy(1);
          category.setUpdatedOn(new Date());
      }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findByIsDeletedFalse();
        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryList = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();

        return categoryList;
    }


    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findByIdAndIsDeletedFalse(id);
        if ((findByCategory.isPresent())) {
            Category category = findByCategory.get();
           return mapper.map(category,CategoryDto.class);
        }
        return null;
    }

    @Override
    public Boolean DeleteCategoryById(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findById(id);

        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            category.setIsDeleted(true);  // Soft delete
            categoryRepository.save(category);  // Save the updated category
            return true;  // Return true if deletion is successful
        }

        return false;  // Return false if the category was not found
    }
}


