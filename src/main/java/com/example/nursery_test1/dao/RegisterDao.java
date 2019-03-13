package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterDao extends JpaRepository<Register,Integer> {
}
