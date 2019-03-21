package com.example.nursery_test1.config;

/*这里负责页面的跳转*/
/*不带数据的那种 纯跳转*/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvc implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:foreHome");


        /*后台*/
        registry.addViewController("/adminLogin").setViewName("admin/login");
        registry.addViewController("/adminHome").setViewName("admin/home");
        registry.addViewController("/adminUserMs").setViewName("admin/user_message");
        registry.addViewController("/userAdd").setViewName("admin/userAdd");
        registry.addViewController("/userManage").setViewName("admin/userManage");
        registry.addViewController("/categoryAdd").setViewName("admin/categoryAdd");
        registry.addViewController("/categoryList").setViewName("admin/categoryList");
        registry.addViewController("/subClass").setViewName("admin/subClass");
        registry.addViewController("/classPage").setViewName("admin/class");
        registry.addViewController("/studentList").setViewName("admin/studentList");
        registry.addViewController("/classStudent").setViewName("admin/classStudent");
        registry.addViewController("/addPropaganda").setViewName("admin/addPropaganda");
        registry.addViewController("/activityList").setViewName("admin/activityList");
        registry.addViewController("/resourceImg").setViewName("admin/resourceImg");
        registry.addViewController("/resourceVideo").setViewName("admin/resourceVideo");


        /*前台*/
        registry.addViewController("/foreLogin").setViewName("fore/login");
        registry.addViewController("/foreHome").setViewName("fore/home");
        registry.addViewController("/zone").setViewName("fore/zone");
        registry.addViewController("/foreRegister").setViewName("fore/register");
        registry.addViewController("/UserInfo").setViewName("fore/UserInfo");
        registry.addViewController("/enroll").setViewName("fore/enroll");
        registry.addViewController("/myRegister").setViewName("fore/myRegister");
        registry.addViewController("/categoryPage").setViewName("fore/categoryPage");
        registry.addViewController("/subClassPage").setViewName("fore/subClassPage");

    }
}
