package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.pojo.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassDao extends JpaRepository<Class,Integer>{
    /*按课程类型跟据学生还能容纳数倒叙查找课程*/
    List<Class> findByTypeOrderByStudentneedDesc(Subclass subclass);
}
