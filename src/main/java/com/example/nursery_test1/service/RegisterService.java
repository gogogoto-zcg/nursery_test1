package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.RegisterDao;
import com.example.nursery_test1.pojo.*;
import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    RegisterDao registerDao;
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    /*判断班级是否还能容纳人数 ，若能容纳就为学生设置班级，并对相关班级的信息做一些更改*/
    public boolean checkClassIsEnough(Register bean){

        Class c=autoMatchingClass(bean.getType());
        if(c==null)
            return false;
        else {
            bean.getStudent().setaClass(c);
            classService.classNumAdd(c);
            return true;
        }
    }


    /*报名*/
    public void add(Register bean) {
        bean.setEnrolltime(new Date());
        registerDao.save(bean);
    }

    /*查询某人所有的报名情况*/
    public Page4Navigator<Register> listByUser(int start, int size, int navigatePages, int uid) {
        User u = userService.get(uid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = registerDao.findByUser(u, pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*确认付款*/
    public void updateIsPay(int rid) {
        Register r = registerDao.getOne(rid);
        r.setPaytime(new Date());
        r.setIspay(true);
        Student student=r.getStudent();
        student.setRegister(true);
        student.setState("学习中");
        studentService.update(student);
        registerDao.save(r);
    }

    /*自动匹配教室*/
    private Class autoMatchingClass(Subclass subclass) {
        List<Class> classList = classService.getByTypeOrderByNeedDesc(subclass);
        Class c=null;
        for (int i = 0; i < classList.size(); i++) {
            if (classList.get(i).getStudentneed() != 0) {
                c = classList.get(i);
                break;
            }
        }
        return c;

    }
}
