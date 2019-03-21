package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Activity;
import com.example.nursery_test1.pojo.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDao extends JpaRepository<Activity,Integer> {
    Page<Activity> findByAClass(Class c, Pageable pageable);
}
