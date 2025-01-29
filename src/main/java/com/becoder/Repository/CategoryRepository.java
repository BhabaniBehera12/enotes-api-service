package com.becoder.Repository;

import com.becoder.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category ,Integer> {
}
