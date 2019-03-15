package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Register;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterDao extends JpaRepository<Register,Integer> {
    Page<Register> findByUser(User u, Pageable pageable);
}
