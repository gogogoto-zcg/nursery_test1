package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByEmailAndRole(String email,String role);
    User findByEmailAndPasswordAndRole(String email,String password,String role);
}
