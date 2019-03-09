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
        registry.addViewController("/").setViewName("admin/login");
        registry.addViewController("/adminHome").setViewName("admin/home");
        registry.addViewController("/adminUserMs").setViewName("admin/user_message");
        registry.addViewController("/userAdd").setViewName("admin/userAdd");
        registry.addViewController("/userManage").setViewName("admin/userManage");
        registry.addViewController("/categoryAdd").setViewName("admin/categoryAdd");
        registry.addViewController("/categoryList").setViewName("admin/categoryList");
        registry.addViewController("/subClass").setViewName("admin/subClass");
    }
}
