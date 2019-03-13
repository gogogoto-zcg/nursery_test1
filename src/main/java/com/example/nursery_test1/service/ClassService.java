package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.ClassDao;
import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    ClassDao classDao;

    /*查询所有班级数据*/
    public Page4Navigator<Class> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Class> pageFromJPA = classDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*增加一个班级*/
    public void add(Class bean) {
        classDao.save(bean);
    }

    /*跟据id删除一个班级*/
    public void delete(int id) {
        classDao.deleteById(id);
    }

    /*跟据id查询一个班级*/
    public Class getOne(int id){
        return classDao.getOne(id);
    }

    /*更新一个班级信息*/
    public void update(Class bean){
        classDao.save(bean);
    }
}
