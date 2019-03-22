package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.pojo.Subclass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassDao extends JpaRepository<Class, Integer> {
    /*按课程类型跟据学生还能容纳数倒叙查找课程*/
    List<Class> findByTypeOrderByStudentneedDesc(Subclass subclass);

    /*跟据名字查询*/
    Page<Class> findByNameLike(String name, Pageable pageable);

    /*跟据班级类型查询*/
    @Query("select c from Class c where c.type.name like?1")
    Page<Class> findByTypeLike(String keyword, Pageable pageable);
}
