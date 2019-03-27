package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.service.ClassService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassController {

    @Autowired
    ClassService classService;

    /**
     * 查找所有教室
     * @param start
     * @param size
     * @return
     */
    @GetMapping("class")
    public Object list(@RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size){
        Page4Navigator<Class> page = classService.list(start, size, 5);
        return Result.success(page);
    }

    /**
     * 新增一个教室
     * @param bean
     * @return
     */
    @PostMapping("class")
    public Object classAdd(@RequestBody Class bean){
        classService.add(bean);
        return Result.success();
    }

    /**
     * 删除一个班级信息
     * @param id
     * @return
     */
    @DeleteMapping("class/{id}")
    public Object deleteById(@PathVariable("id") int id){
        classService.delete(id);
        return Result.success();
    }

    /**
     * 获取一个班级信息
     * @param id
     * @return
     */
    @GetMapping("class/{id}")
    public Object getOne(@PathVariable("id")int id){
        Class c=classService.getOne(id);
        return Result.success(c);
    }


    /**
     * 更新一个班级信息
     * @param bean
     * @return
     */
    @PutMapping("class")
    public Object update(@RequestBody Class bean){
        classService.update(bean);
        return Result.success();
    }

    /**
     * 跟据查询类型以及查询关键字查询字符串
     * @param start
     * @param size
     * @param type
     * @param keyword
     * @return
     */
    @GetMapping("lookup")
    public Object lookup(@RequestParam(value = "start", defaultValue = "0") int start,
                         @RequestParam(value = "size", defaultValue = "5") int size,
                         @RequestParam(value = "type",defaultValue = "")String type,
                         @RequestParam(value = "keyword",defaultValue = "")String keyword){
        Page4Navigator<Class> page=null;
        if(type.equals("name")){
            page=classService.listByNameLike(start,size,5,keyword);
        }
        else if(type.equals("type")){
            page=classService.listByTypeLike(start,size,5,keyword);
        }else {
            return null;
        }
        return Result.success(page);
    }

}
