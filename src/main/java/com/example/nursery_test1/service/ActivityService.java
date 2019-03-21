package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.ActivityDao;
import com.example.nursery_test1.pojo.Activity;
import com.example.nursery_test1.pojo.Class;
import com.example.nursery_test1.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    ActivityDao activityDao;
    @Autowired
    ClassService classService;

    /*更新一个活动*/
    public void updateById(Activity bean){
        activityDao.save(bean);
    }
    /*查找一个活动*/
    public Activity getOne(int id){
        return activityDao.getOne(id);
    }

    /*增加一个活动并且返回带id的对象*/
    public Activity add(Activity bean){
        return activityDao.save(bean);
    }

    /*删除一个活动*/
    public  void delete( int id){
        activityDao.deleteById(id);
    }

    /*修改活动*/
    public void  update(Activity bean){
        activityDao.save(bean);
    }
    /*查找所有班级对应的活动*/
    public Page4Navigator<Activity> getByClass(int  cid,int start, int size, int navigatePages){
        Class c = classService.getOne(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = activityDao.findByAClass(c,pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

}
