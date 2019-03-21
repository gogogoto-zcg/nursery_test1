package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.service.StudentService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 增加一个学生
     *
     * @param bean
     * @param img
     * @return
     */
    @PostMapping("student")
    public Object add(Student bean, MultipartFile[] img) {
        Student student = studentService.add(bean, img);
//studentService.add(bean, img);
        return Result.success(student);
    }


    /**
     * 查找某用户的托管生
     *
     * @param uid
     * @return
     */
    @GetMapping("user/{uid}/student")
    public Object listByUser(@PathVariable("uid") int uid,
                             @RequestParam("isRegister") boolean isRegister) {
        List<Student> studentList = studentService.list(uid, isRegister);
        return Result.success(studentList);
    }

    /**
     * 查询所有已入学的学生信息
     *
     * @param start
     * @param size
     * @param b
     * @return
     */
    @GetMapping("student")
    public Object listByRegister(@RequestParam(value = "start", defaultValue = "0") int start,
                                 @RequestParam(value = "size", defaultValue = "5") int size,
                                 @RequestParam(value = "isRegister", defaultValue = "0") boolean b) {
        Page4Navigator<Student> page = studentService.listByRegister(start, size, 5, b);
        return Result.success(page);
    }

    /**
     * 删除指定学生
     * @param id
     * @return
     */
    @DeleteMapping("student/{id}")
    public Object deleteById(@PathVariable("id") int id) {
        studentService.delete(id);
        return Result.success();
    }


    /**
     * 更新某学生的学习状态
     * @param id
     * @param state
     * @return
     */
    @PutMapping("student/{id}")
    public Object update(@PathVariable("id")int id,
                         @RequestParam("state")String state){
        studentService.updateByState(id,state);
        return Result.success();
    }

    @GetMapping("class/{cid}/student")
    public Object listByClass(@RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              @RequestParam(value = "isRegister", defaultValue = "0")boolean b,
                              @PathVariable("cid")int cid){
        Page4Navigator<Student> page=studentService.listByRegisterAndClass(start,size,5,b,cid);
        return  Result.success(page);
    }
}
