package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer> {
    List<Student> findByUserAndIsRegister(User u, boolean b);

    List<Student> findByUser(User u);

//    @Query("select s from Student s where s.register=?1")
//    Page<Student> findByIsRegister(boolean b, Pageable pageable);

    Page<Student> findByIsRegister(boolean b, Pageable pageable);

    Page<Student> findByIsRegisterAndAClass(boolean b, Class c, Pageable pageable);


    /*按班级模糊查询*/
    Page<Student> findByNameLikeAndIsRegisterAndAClass(String name,boolean b, Class c, Pageable pageable);

    Page<Student> findByAgeLikeAndIsRegisterAndAClass(String age,boolean b, Class c, Pageable pageable);

    Page<Student> findBySexLikeAndIsRegisterAndAClass(String sex,boolean b, Class c, Pageable pageable);

//    @Query("select s from Student s where (s.name like ?1 or s.age like ?1 or s.sex like ?1) and s.aClass=?3 and s.register=?2")
//    Page<Student> findByAllLikeAndRegisterAndAClass(String s, boolean b,Class c, Pageable pageable);

    /*模糊查询已入学的学生*/
    @Query("select s from Student s where s.name like?1 and s.isRegister = ?2")
    Page<Student> findByNameLikeAndIsRegister(String name,Boolean b,Pageable pageable);

    @Query("select s from Student s join s.aClass c where c.name like?1 and s.isRegister = ?2")
    Page<Student> findByClassNameLikeAndIsRegister(String s, boolean b, Pageable pageable);

    @Query("select s from Student s join s.user u where u.name like?1 and s.isRegister = ?2")
    Page<Student> findByParentNameLikeAndIsRegister(String s, boolean b, Pageable pageable);
}
