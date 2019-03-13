package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.service.StudentService;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("student")
    public Object add(Student bean, MultipartFile[] img) throws FileNotFoundException {
        Student student=studentService.add(bean, img);
//studentService.add(bean, img);
        return Result.success(student);
    }
}
