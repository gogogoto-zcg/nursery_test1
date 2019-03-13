package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.StudentDao;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student add(Student bean, MultipartFile[] img) throws FileNotFoundException {
        Student student=studentDao.save(bean);
        if (img.length != 0) {
            String img_folder = "static/img/student";
            String img_path = ResourceUtils.getURL("classpath:").getPath() + img_folder;
            String img_name = bean.getId() + ".jpg";
            if (FileUtils.upload(img[0], img_path, img_name))
                bean.setImage(img_name);
            student=studentDao.save(bean);
        }
        return student;
    }
}
