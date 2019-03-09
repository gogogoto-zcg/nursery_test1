package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Category;
import com.example.nursery_test1.pojo.Subclass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubclassDao extends JpaRepository<Subclass,Integer>{
    Page<Subclass> findByCategory(Category category, Pageable pageable);
    List<Subclass> findByCategory(Category category);
}
