package com.example.nursery_test1.web;

import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Result;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/*放一些无需交互数据库的控制器*/
@RestController
public class CommonController {

    /*首页宣传视频的上传*/
    @PostMapping("propaganda")
    public Object addPropaganda(MultipartFile[] file,HttpServletRequest request) {
        if (file.length != 0) {
            String video_folder = "static/video/propaganda";
            String video_path=null;
            try {
                 video_path = ResourceUtils.getURL("classpath:").getPath() + video_folder;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String video_name = "宣传视频.mp4";
            if(FileUtils.upload_video(file[0],video_path,video_name));
            else return Result.fail("上传失败");
        }
        return Result.success();
    }
}
