package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
