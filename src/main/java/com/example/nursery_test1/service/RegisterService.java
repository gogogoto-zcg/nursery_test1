package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.RegisterDao;
import com.example.nursery_test1.pojo.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    RegisterDao registerDao;


    public void add(Register bean){
        registerDao.save(bean);
    }
}
