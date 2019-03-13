package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.service.UserService;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@RestController
public class UserContoller {
    @Autowired
    UserService userService;

    /**
     * 查询所有用户信息以及分页信息
     *
     * @param start 起始页
     * @param size  显示个数
     * @return Page4Navigator<User>
     */
    @GetMapping("user")
    public Object list(@RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        start = start < 0 ? 0 : start;
        Page4Navigator<User> page = userService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return Result.success(page);
    }


    /**
     * 更新用户信息
     *
     * @param bean 待更新用户信息
     * @param img  头像图片
     * @return
     * @throws FileNotFoundException
     */
    @PutMapping("user")
    public Object update(User bean, MultipartFile[] img, HttpSession session) throws FileNotFoundException {
        Boolean b = userService.update(bean, img, session);
        if (!b) return Result.fail("更新失败");
        return Result.success();
    }

    /**
     * 增加一个用户
     *
     * @param bean
     * @return
     */
    @PostMapping("user")
    public Object userAdd(@RequestBody User bean) {
        userService.add(bean);
        return Result.success();
    }

    /**
     * 跟据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("user/{id}")
    public Object deleteById(@PathVariable("id") int id) {
        userService.delete(id);
        return Result.success();
    }


    /**
     * 跟据id查询一个用户
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("user/{id}")
    public Object getById(@PathVariable("id") int id) {
        User bean = userService.get(id);
        return Result.success(bean);
    }

    /**
     * 登陆
     *
     * @param bean    账号密码的bean
     * @param session session对象
     * @return 反馈信息
     */
    @PostMapping("login")
    public Object login(@RequestBody User bean, HttpSession session) {
        Boolean b = userService.judgeIsLogin(bean, session);
        if (b) {
            return Result.success();
        } else return Result.fail("登陆失败");
    }

    /**
     * 注册
     *
     * @param bean 注册用户
     * @return 反馈信息
     */
    @PostMapping("register_1")
    public Object register(@RequestBody User bean) {
        Boolean b = userService.isHaved(bean);
        if (b) return Result.fail("用户名已存在");
        else {
            userService.add(bean);
            return Result.success();
        }
    }


}
