package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer> {
    List<Student> findByUserAndIsRegister(User u,boolean b);
    List<Student> findByUser(User u);

//    @Query("select s from Student s where s.register=?1")
//    Page<Student> findByIsRegister(boolean b, Pageable pageable);

    Page<Student> findByIsRegister(boolean b, Pageable pageable);
}
