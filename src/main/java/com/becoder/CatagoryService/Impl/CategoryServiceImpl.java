package com.becoder.CatagoryService.Impl;


import com.becoder.CatagoryDto.CategoryDto;
import com.becoder.CatagoryDto.CategoryResponse;
import com.becoder.CatagoryService.CategoryService;
import com.becoder.Entity.Category;
import com.becoder.Exception.ExistDataException;
import com.becoder.Exception.ResourceNotFoundException;
import com.becoder.Exception.Validation;
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

    private Validation validation;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper, Validation validation) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {


//validation checking
        validation.categoryValiditaion(categoryDto);

        //check category exsit or not
        Boolean exist=categoryRepository.existsByName(categoryDto.getName().trim());
       if (exist)
       {
           //throw error
           throw  new ExistDataException("Category already exist");

       }
        Category category=mapper.map(categoryDto, Category.class);
        if (ObjectUtils.isEmpty(category.getId())){
            category.setIsDeleted(false);
//            category.setCreatedBy(1);
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
          //category.setIsDeleted(existcategory.getIsDeleted());
//          category.setUpdatedBy(1);
//          category.setUpdatedOn(new Date());
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
    public CategoryDto getCategoryById(Integer id) throws Exception{
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                ()->new ResourceNotFoundException("category not found with id="+id));

        if (!ObjectUtils.isEmpty(category)) {
         category.getName().toUpperCase();
            
//            if (category .getName() ==null)
//            {
//                throw new IllegalArgumentException("name is null");
//            }
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


