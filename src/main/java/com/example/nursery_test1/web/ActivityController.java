package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Activity;
import com.example.nursery_test1.pojo.ActivityResource;
import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.service.ActivityResourceService;
import com.example.nursery_test1.service.ActivityService;
import com.example.nursery_test1.service.ClassService;
import com.example.nursery_test1.service.UserService;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.Date;

@RestController
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityResourceService activityResourceService;
    @Autowired
    UserService userService;
    @Autowired
    ClassService classService;

    /*增加一个班级活动同时上传活动资源*/
    @PostMapping("aClass/{cid}/activity")
    @Transactional
    public Object addActivity(Activity bean,
                              @RequestParam("uid") int uid,
                              @RequestParam("uploadFiles") MultipartFile[] files,
                              @PathVariable("cid") int cid) {
        Class c = classService.getOne(cid);
        User u = userService.get(uid);
        bean.setUploadTime(new Date());
        bean.setUser(u);
        bean.setaClass(c);
        /*上传的活动bean*/
        Activity activity = activityService.add(bean);


        /*班级文件夹*/
        String classFolder = "activity/" + activity.getaClass().getName();
        /*活动文件夹*/
        String activeFolder = classFolder + "/" + activity.getIntroduce();
        /*上传文件名*/
        String filename;
        /*文件后缀名*/
        String name;
        /*保存的某个活动的视频文件夹*/
        String videoFolder;
        /*保存的视频文件名*/
        String mp4Name;
        /*保存的图片文件夹*/
        String imgFolder;
        /*保存的图片名*/
        String imgName;
        /*文件路径*/
        String path;


        if (files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                filename = files[i].getOriginalFilename();
                name = filename.substring(filename.lastIndexOf(".") + 1);
                /*文件格式为MP4时*/
                if (name.equals("mp4")) {
                    ActivityResource ar =new ActivityResource();
                    ar.setFileType("mp4");
                    ar.setActivity(activity);
                    ActivityResource activityResource = activityResourceService.add(ar);
                    videoFolder = activeFolder + "/mp4/";
                    mp4Name = activityResource.getId() + ".mp4";
                    try {
                        path = ResourceUtils.getURL("classpath:").getPath()+"static/" + videoFolder;
                        if (FileUtils.upload_video(files[i], path, mp4Name)) {
                            activityResource.setFile_url(videoFolder+mp4Name);
                            activityResourceService.add(activityResource);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    /*文件格式为jpg时*/
                } else if (name.equals("jpg")) {
                    ActivityResource ar =new ActivityResource();
                    ar.setFileType("jpg");
                    ar.setActivity(activity);
                    ActivityResource activityResource = activityResourceService.add(ar);
                    imgFolder = activeFolder + "/img/";
                    imgName = activityResource.getId() + ".jpg";
                    try {
                        path = ResourceUtils.getURL("classpath:").getPath()+"static/" + imgFolder;
                        if (FileUtils.upload_video(files[i], path, imgName)) {
                            activityResource.setFile_url(imgFolder+imgName);
                            activityResourceService.add(activityResource);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.success();
    }

    /*查找某个班级的所有活动*/
    @GetMapping("aClass/{cid}/activity")
    public Object listByClass(@RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              @PathVariable("cid")int cid){
        Page4Navigator<Activity> page=activityService.getByClass(cid,start,size,5);
        return Result.success(page);
    }

    /*删除某个活动*/
    @DeleteMapping("activity/{id}")
    public Object deleteById(@PathVariable("id") int id){
        activityService.delete(id);
        return Result.success();
    }

    /*获取某个班级活动*/
    @GetMapping("activity/{id}")
    public Object getById(@PathVariable("id")int id){
        Activity a = activityService.getOne(id);
        return Result.success(a);
    }

    /*更新某个活动*/
    @PutMapping("activity")
    public Object updateById(@RequestBody Activity bean){
        activityService.updateById(bean);
        return  Result.success();
    }

}
