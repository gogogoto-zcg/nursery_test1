package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.CategoryDao;
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

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    SubclassService subclassService;

    /*查询一个分类*/
    public Category getOne(int id) {
        return categoryDao.getOne(id);
    }

    /*新增一个分类*/
    public void add(Category bean) {
        categoryDao.save(bean);
    }

    /*删除一个分类的条件判定*/
    public boolean deleteById(int id) {
        Category category = getOne(id);
        List<Subclass> subclasses=subclassService.listByCategory(category);
        /*该教学性质下没有子分类,允许删除*/
        if(subclassService.listByCategory(category).size()==0) {
            categoryDao.deleteById(id);
            return  true;
        }
        else
            return false;
    }

    /*更新一个分类信息*/
    public void update(Category bean) {
        categoryDao.save(bean);
    }

    /*查询所有分类信息以及分页信息*/
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }
}
