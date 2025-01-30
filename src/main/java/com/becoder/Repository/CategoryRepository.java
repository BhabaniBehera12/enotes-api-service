package com.becoder.Repository;

import com.becoder.Entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category ,Integer> {

    List<Category> findByIsActiveTrueAndIsDeletedFalse();
    Optional<Category>findByIdAndIsDeletedFalse(int id);
    List<Category>findByIsDeletedFalse();




}
