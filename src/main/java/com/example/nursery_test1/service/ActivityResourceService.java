package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.ActivityResourceDao;
import com.example.nursery_test1.pojo.Activity;
import com.example.nursery_test1.pojo.ActivityResource;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityResourceService {
    @Autowired
    ActivityResourceDao activityResourceDao;
    @Autowired
    ActivityService activityService;

    /*增加活动图片或视频*/
    public ActivityResource add(ActivityResource bean) {
        return activityResourceDao.save(bean);
    }

    /*删除一个图片或者视频*/
    public void deleteById(int id) {
        activityResourceDao.deleteById(id);
    }

    /*跟据活动内容按文件类型查询*/
    public Page4Navigator<ActivityResource>  listByFileTypeAndActivity(
            int id,String fileType,int start, int size, int navigatePages){
        Activity activity = activityService.getOne(id);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = activityResourceDao.findByActivityAndFileType(activity,fileType,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }
}
