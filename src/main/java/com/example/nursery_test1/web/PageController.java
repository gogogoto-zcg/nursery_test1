package com.example.nursery_test1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
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
}
