package com.example.nursery_test1.dao;

import com.example.nursery_test1.pojo.Activity;
import com.example.nursery_test1.pojo.ActivityResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityResourceDao extends JpaRepository<ActivityResource,Integer>{
    Page<ActivityResource> findByActivityAndFileType(Activity activity, String fileType, Pageable p);
}
