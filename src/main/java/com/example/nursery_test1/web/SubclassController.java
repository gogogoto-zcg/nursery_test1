package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Subclass;
import com.example.nursery_test1.service.SubclassService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.OpenMBeanConstructorInfo;

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
    public Object subclassAdd(@RequestBody Subclass bean) {
        subclassService.add(bean);
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
     * 删除一个子分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("subclass/{id}")
    public Object delete(@PathVariable("id") int id) {
        subclassService.delete(id);
        return Result.success();
    }

    /**
     * 查询一个子分类
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
     * @param bean
     * @return
     */
    @PutMapping("subclass")
    public Object update(@RequestBody Subclass bean) {
        subclassService.update(bean);
        return Result.success();
    }
}
