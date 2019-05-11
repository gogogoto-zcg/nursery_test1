package com.example.nursery_test1.web;

import com.example.nursery_test1.pojo.Register;
import com.example.nursery_test1.pojo.Student;
import com.example.nursery_test1.service.RegisterService;
import com.example.nursery_test1.util.Page4Navigator;
import com.example.nursery_test1.util.Result;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    /**
     * 报名
     * @param bean
     * @return
     */
    @PostMapping("register")
    public Object add(Register bean) {
        if(registerService.checkClassIsEnough(bean)){
            registerService.add(bean);
            return Result.success();
        }
        else return Result.fail("报名失败");

    }

    /**
     *查找某用户的报名情况
     * @param start
     * @param size
     * @param uid
     * @return
     */
    @GetMapping("user/{uid}/register")
    public Object listByUser(@RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "size", defaultValue = "5") int size,
                             @PathVariable("uid") int uid) {
        Page4Navigator<Register> page = registerService.listByUser(start, size, 5, uid);
        return Result.success(page);
    }

//    /*确认付款*/
//    @GetMapping("register/{id}")
//    public String updateIsPay(@PathVariable("id") String id){
//        int oid=Integer.parseInt(id);
//        registerService.updateIsPay(oid);
//        return "付款成功";
//    }

//    /*确认付款*/
//    @RequestMapping("register/{id}")
//    public String updateIsPay(@PathVariable("id") String id){
//        int oid=Integer.parseInt(id);
//        registerService.updateIsPay(oid);
//        return "fore/myRegister";
//    }
}
