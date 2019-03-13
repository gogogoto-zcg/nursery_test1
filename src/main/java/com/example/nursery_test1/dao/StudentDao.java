package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Integer> {
}
