package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.ActivityResource;
import com.example.nursery_test1.service.ActivityResourceService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityResourceController {
    @Autowired
    ActivityResourceService activityResourceService;

    /**
     * 查询某一个活动下的某种类型资源
     * @param start
     * @param size
     * @param aid       活动
     * @param fileType 文件类型
     * @return
     */
    @GetMapping("activity/{aid}/resource")
    public Object listByActivityAndFileType(@RequestParam(value = "start", defaultValue = "0") int start,
                                            @RequestParam(value = "size", defaultValue = "5") int size,
                                            @PathVariable("aid") int aid,
                                            @RequestParam("fileType") String fileType) {
        Page4Navigator<ActivityResource> page=activityResourceService.listByFileTypeAndActivity(aid,fileType,start,size,5);
        return Result.success(page);
    }


    @DeleteMapping("resource/{id}")
    public  Object deleteById(@PathVariable("id") int id){
        activityResourceService.deleteById(id);
        return Result.success();
    }

}
