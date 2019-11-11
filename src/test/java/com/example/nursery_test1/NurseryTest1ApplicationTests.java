package com.example.nursery_test1;

import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.service.UserService;
import com.example.nursery_test1.util.Page4Navigator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NurseryTest1ApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        ///
        Date d=new Date();
        System.out.println(d);
        String s;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");
        sdf.format(d);
        System.out.println("当前时间："+sdf.format(d));
        Calendar now=Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int weeks=now.get(Calendar.WEEK_OF_YEAR);
        System.out.println("今年是"+year+"年的第"+weeks+"周");
    }

    @Test
    public void testUserController() {
        Page4Navigator<User> page = userService.list(2, 1, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        List<User> users = page.getContent();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
