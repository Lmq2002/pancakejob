package com.jbgz.pancakejob.controller;


import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot";
    }

    @PostMapping("/find")
    public int find(@RequestBody User user){
        //仅供参考
        return userService.find(user);
    }

    @GetMapping("/page")
    public List<User> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize)
    {
        // 仅供参考，如何获取参数
        return null;
    }
}
