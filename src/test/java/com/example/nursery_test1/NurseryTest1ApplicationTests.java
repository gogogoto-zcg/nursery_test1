package com.example.nursery_test1;

import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.service.UserService;
import com.example.nursery_test1.util.Page4Navigator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NurseryTest1ApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {

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
