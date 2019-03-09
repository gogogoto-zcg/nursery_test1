package com.example.nursery_test1.service;

import com.example.nursery_test1.dao.UserDAO;
import com.example.nursery_test1.pojo.User;
import com.example.nursery_test1.util.FileUtils;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void add(User bean) {
        userDAO.save(bean);
    }

    public void delete(int id) {
        userDAO.deleteById(id);
    }

    /*更新用户信息，并保存头像*/
    public boolean update(User bean, MultipartFile[] img,HttpSession session) throws FileNotFoundException {
        if (img.length != 0) {
            String img_folder = "static/img/user";
            String img_path = ResourceUtils.getURL("classpath:").getPath() + img_folder;
            String img_name = bean.getId() + ".jpg";
            if (FileUtils.upload(img[0], img_path, img_name))
                bean.setPortrait(img_name);
            else
                return false;
        }
        userDAO.save(bean);
        User user_session=(User) session.getAttribute("user");
        /*如果更新的信息为当前用户，则更新session的用户信息*/
        if(bean.getId()==user_session.getId())
        session.setAttribute("user",bean);
        return true;
    }

    public User get(int id) {
        return userDAO.getOne(id);
    }

    /*查询所有用户信息以及分页数据*/
    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /*跟据账号邮箱判断是否已经存在*/
    public boolean isHaved(User bean) {
        User user = userDAO.findByName(bean.getEmail());
        if (user != null)
            return true;
        else
            return false;
    }

    /*判断是否能够登陆*/
    public boolean judgeIsLogin(User bean, HttpSession session) {
        User user = userDAO.findByEmailAndPassword(bean.getEmail(), bean.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return true;
        } else
            return false;
    }

}
