package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Register;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.service.RegisterService;
import com.example.nursery_test1.util.Result;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @PostMapping("register")
    public Object add(Register bean){
        registerService.add(bean);
        return Result.success();
    }
}
