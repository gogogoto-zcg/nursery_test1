package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.SubclassDao;
import com.example.nursery_test1.pojo.Category;
import com.example.nursery_test1.pojo.Subclass;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class SubclassService {
    @Autowired
    SubclassDao subclassDao;
    @Autowired
    CategoryService categoryService;

    /*为某分类增加子分类*/
    public void add( Subclass bean) {
        subclassDao.save(bean);
    }

    public List<Subclass> listByCategory(Category category){
        return subclassDao.findByCategory(category);
    }

    /*查询某分类下的所有子分类以及分页信息*/
    public Page4Navigator<Subclass> list(int cid, int start, int size,int navigatePages){
        Category category=categoryService.getOne(cid);
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            Pageable pageable = new PageRequest(start, size, sort);
            Page<Subclass> pageFromJPA=subclassDao.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    /*查询所有子分类*/
    public List<Subclass> list(){
        return subclassDao.findAll();
    }

    /*删除某分类的子分类*/
    public void delete(int id){
        subclassDao.deleteById(id);
    }

    /*查询某个子分类*/
    public Subclass getOne(int id){
        return subclassDao.getOne(id);
    }

    /*更新某个子分类*/
    public void  update(Subclass bean){
        subclassDao.save(bean);
    }

}
