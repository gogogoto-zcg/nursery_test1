package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Subclass;
import com.example.nursery_test1.service.SubclassService;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.openmbean.OpenMBeanConstructorInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class SubclassController {
    @Autowired
    SubclassService subclassService;

    /**
     * 为某分类增加子分类
     *
     * @param bean 子分类信息
     * @return
     */
    @PostMapping("subclass")
    public Object subclassAdd(Subclass bean, MultipartFile[] file) {
        Subclass s=subclassService.add(bean);
        if (file.length != 0) {
            String img_folder = "img/subclass";
            String img_name = String.valueOf(s.getId()) + ".jpg";
            String img_path = null;
            try {
                img_path = ResourceUtils.getURL("classpath:").getPath() + "static/" + img_folder;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (FileUtils.upload(file[0], img_path, img_name))
                s.setImg(img_folder + "/" + img_name);
            else
                return Result.fail("文件上传失败");
            subclassService.update(s);
        }
        return Result.success();
    }

    /**
     * 查询某分类下的所有子分类
     *
     * @param start
     * @param size
     * @param cid
     * @return
     */
    @GetMapping("category/{cid}/subclass")
    public Object list(@RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @PathVariable("cid") int cid) {
        Page4Navigator<Subclass> page = subclassService.list(cid, start, size, 5);
        return Result.success(page);
    }

    /**
     * 查询所有子分类，也就是班级类型
     * @return
     */
    @GetMapping("subclass")
    public Object list() {
        List<Subclass> subclasses = subclassService.list();
        return Result.success(subclasses);
    }

    /**
     * 删除一个子分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("subclass/{id}")
    public Object delete(@PathVariable("id") int id) {
        subclassService.delete(id);
        String img_folder = "img/subclass";
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
     * 查询一个子分类
     *
     * @param id
     * @return
     */
    @GetMapping("subclass/{id}")
    public Object getOne(@PathVariable("id") int id) {
        Subclass subclass = subclassService.getOne(id);
        return Result.success(subclass);
    }

    /**
     * 更新一个子分类
     *
     * @param bean
     * @return
     */
    @PutMapping("subclass")
    public Object update(Subclass bean,MultipartFile[] file) {
        if (file.length != 0) {
            String img_folder = "img/subclass";
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
        subclassService.update(bean);
        return Result.success();
    }



}
