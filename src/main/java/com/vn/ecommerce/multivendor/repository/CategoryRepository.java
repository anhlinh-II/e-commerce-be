package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(String categoryId);
}
