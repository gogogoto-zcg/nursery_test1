package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByName(String email);
    User findByEmailAndPasswordAndRole(String email,String password,String role);
}
