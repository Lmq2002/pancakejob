package com.jbgz.pancakejob.controller;


import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.LoginDTO;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 功能：注册用户
     * 状态：正在开发中
     * 负责人:Lmq
     * 新建时间：2022/12/28
     * */
    @PostMapping("/register")
    public ResultData regist(@RequestBody User user)
    {
        userService.save(user);
        return ResultData.error();
    }

    /**
     * 功能：登录，记录conlog
     * 状态：正在开发中
     * 负责人：Lmq
     * 新建时间：2022/12/28
     * */
    @PostMapping("/login")
    public ResultData login(@RequestBody Map<String,Object> inputData)
    {
//        if(userService.find(logindto)!=null)
//        {
//            //...
//            //conlogService.save()
//        }
        return ResultData.error(Constants.CODE_401,"test");
    }

    /**
     * 功能：修改密码
     * 状态：正在开发中
     * 负责人：Lmq
     * 新建时间：2022/12/28
     * */
    @PutMapping("/password")
    public ResultData modifyPassword(@RequestBody User user)
    {
        return ResultData.error();
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot";
    }
//
//    @PostMapping("/find")
//    public int find(@RequestBody User user){
//        //仅供参考
//        return userService.find(user);
//    }
//
//    @GetMapping("/page")
//    public List<User> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize)
//    {
//        // 仅供参考，如何获取参数
//        return null;
//    }
}
