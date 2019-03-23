package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.StudentDao;
import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    /*新增一个学生*/
    public Student add(Student bean, MultipartFile[] img) {
        Student student = studentDao.save(bean);
        if (img.length != 0) {
            String img_folder = "static/img/student";
            String img_path = null;
            try {
                img_path = ResourceUtils.getURL("classpath:").getPath() + img_folder;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String img_name = bean.getId() + ".jpg";
            if (FileUtils.upload(img[0], img_path, img_name))
                bean.setImage(img_name);
            student = studentDao.save(bean);
        }
        return student;
    }

    /*查出指定用户的未报名的学员*/
    public List<Student> list(int uid, boolean isRegister) {
        User u = userService.get(uid);
        if (isRegister)
            return studentDao.findByUserAndIsRegister(u, isRegister);
        else
            return studentDao.findByUser(u);

    }

    /*更新*/
    public void update(Student bean) {
        studentDao.save(bean);
    }

    /*查找所有入学的学生信息并分页处理*/
    public Page4Navigator<Student> listByRegister(int start, int size, int navigatePages,Boolean b){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByIsRegister(b,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*删除指定学生*/
    public void delete(int id){
        studentDao.deleteById(id);
    }

    /*查询某学生*/
    public Student get(int id){
        return  studentDao.getOne(id);
    }

    /*更改某学生的state,同时更改是否入学标志*/
    public void updateByState(int id,String state){
        Student s=get(id);
        s.setRegister(false);
        s.setState(state);
        studentDao.save(s);
    }


    /*查找某班的所有学生并分页*/
    public Page4Navigator<Student> listByRegisterAndClass(int start, int size, int navigatePages,boolean b,int cid){
        Class c=classService.getOne(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByIsRegisterAndAClass(b,c,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*模糊查找某班的学生并分页*/
    public Page4Navigator<Student> listByNameAndClass(int start, int size, int navigatePages,boolean b,Class c,String keyword){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByNameLikeAndIsRegisterAndAClass("%"+keyword+"%",b,c,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*模糊查找某班的学生并分页*/
    public Page4Navigator<Student> listByAgeAndClass(int start, int size, int navigatePages,boolean b,Class c,String keyword){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByAgeLikeAndIsRegisterAndAClass("%"+keyword+"%",b,c,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }
    /*模糊查找某班的学生并分页*/
    public Page4Navigator<Student> listBySexAndClass(int start, int size, int navigatePages,boolean b,Class c,String keyword){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findBySexLikeAndIsRegisterAndAClass("%"+keyword+"%",b,c,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

//    /*模糊查找某班的学生并分页*/
//    public Page4Navigator<Student> listByAllAndClass(int start, int size, int navigatePages,boolean b,Class c,String keyword){
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(start, size, sort);
//        Page<Student> pageFromJPA = studentDao.findByAllLikeAndRegisterAndAClass("%"+keyword+"%",b,c,pageable);
//        return new Page4Navigator<>(pageFromJPA, navigatePages);
//    }


    /*模糊查找已入学学生并分页*/
    public Page4Navigator<Student> listByName(int start, int size, int navigatePages,boolean b,String keyword){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByNameLikeAndIsRegister("%"+keyword+"%",b,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public Page4Navigator<Student> listByClass(int start, int size, int navigatePages, boolean b, String keyword) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByClassNameLikeAndIsRegister("%"+keyword+"%",b,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public Page4Navigator<Student> listByParentName(int start, int size, int navigatePages, boolean b, String keyword) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Student> pageFromJPA = studentDao.findByParentNameLikeAndIsRegister("%"+keyword+"%",b,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }
}
