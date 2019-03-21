package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Category;
import com.example.nursery_test1.service.CategoryService;
import com.example.nursery_test1.service.SubclassService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    SubclassService subclassService;

    /**
     * 查找一个分类
     *
     * @param id
     * @return
     */
    @GetMapping("category/{id}")
    public Object getOneById(@PathVariable("id") int id) {
        Category bean = categoryService.getOne(id);
        return Result.success(bean);
    }


    /**
     * 增加一个分类
     *
     * @param bean
     * @return
     */
    @PostMapping("category")
    public Object categoryAdd(@RequestBody Category bean) {
        categoryService.add(bean);
        return Result.success();
    }


    /**
     * 删除一个分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("category/{id}")
    public Object deleteById(@PathVariable("id") int id) {
        Boolean b = categoryService.deleteById(id);
        if (b)
            return Result.success();
        else
            return Result.fail("该教学性质下还有子分类");
    }

    /**
     * 更新一个分类信息
     *
     * @param bean
     * @return
     */
    @PutMapping("category")
    public Object update(@RequestBody Category bean) {
        categoryService.update(bean);
        return Result.success();
    }

    /**
     * 查询所有分类信息
     *
     * @return
     */
    @GetMapping("category")
    public Object listCategory(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "5") int size) {
        Page4Navigator<Category> page = categoryService.list(start, size, 5);
        categoryService.setSubclassList(page.getContent());
        return Result.success(page);
    }


}
