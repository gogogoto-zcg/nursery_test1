package com.example.nursery_test1.web;

import com.example.nursery_test1.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    RegisterService registerService;

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        /*重定向 ：当前页面重新发送一次请求，这里重定向到登陆*/
        return "redirect:adminLogin";
    }
    @GetMapping("foreLogout")
    public String foreLogout(HttpSession session){
        session.removeAttribute("user");
        /*重定向 ：当前页面重新发送一次请求，这里重定向到登陆*/
        return "redirect:foreLogin";
    }

    /*确认付款*/
    @GetMapping("register/{id}")
    public String updateIsPay(@PathVariable("id") String id){
        int oid=Integer.parseInt(id);
        registerService.updateIsPay(oid);
        System.out.println(oid);
        return "redirect:/";
    }
}
