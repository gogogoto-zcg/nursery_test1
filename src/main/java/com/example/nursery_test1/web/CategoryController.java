package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Category;
import com.example.nursery_test1.service.CategoryService;
import com.example.nursery_test1.service.SubclassService;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
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
    public Object categoryAdd(Category bean, MultipartFile[] file) {
        Category c = categoryService.add(bean);
        if (file.length != 0) {
            String img_folder = "category1";
            String img_name = String.valueOf(c.getId()) + ".jpg";
            String img_path = null;
            try {
                img_path = ResourceUtils.getURL("classpath:").getPath() + "static/" + img_folder;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (FileUtils.upload(file[0], img_path, img_name))
                c.setImg(img_folder + "/" + img_name);
            else
                return Result.fail("文件上传失败");

            categoryService.update(c);
        }
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
        String img_folder = "category1";
        String img_name = id + ".jpg";
        String img_path = null;
        try {
            img_path = ResourceUtils.getURL("classpath:").getPath() + "static/" + img_folder;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File f = new File(img_path + "/" + img_name);
        f.delete();
        return Result.success();
    }

    /**
     * 更新一个分类信息
     *
     * @param bean
     * @return
     */
    @PutMapping("category")
    public Object update(Category bean, MultipartFile[] file) {
        if (file.length != 0) {
            String img_folder = "category1";
            String img_name = String.valueOf(bean.getId()) + ".jpg";
            String img_path = null;
            try {
                img_path = ResourceUtils.getURL("classpath:").getPath() + "static/" + img_folder;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (FileUtils.upload(file[0], img_path, img_name))
                bean.setImg(img_folder + "/" + img_name);
            else
                return Result.fail("文件上传失败");
        }
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
