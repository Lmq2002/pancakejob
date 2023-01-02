package com.jbgz.pancakejob.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.CreateAccountDTO;
import com.jbgz.pancakejob.dto.LoginDTO;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.service.impl.UserServiceImpl;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.EmailAccountVO;
import com.jbgz.pancakejob.vo.FindPasswordVO;
import com.jbgz.pancakejob.vo.RegistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 功能：获取邮箱验证码
     * 状态：正在测试中
     * 负责人：Lmq
     * 新建时间：2023/1/1
     * 完成时间：2023/1/1
     * */
    @PostMapping("/register/email")
    public ResultData createAccount(@RequestBody EmailAccountVO vo){
        UserService service = new UserServiceImpl();
        try{
            CreateAccountDTO dto = service.createAccount(vo);
            if(dto!=null){
                ResultData result = new ResultData(Constants.CODE_200,"SUCCESS",new HashMap<>());
                result.data.put("captcha",dto.getCaptcha());
                return result;
            }
        }
        catch (Exception e){
            System.out.println("异常信息"+e.getMessage());
            return ResultData.error(Constants.CODE_400,"FAILED TO SEND EMAIL");
        }
        return ResultData.error(Constants.CODE_401,"test");
    }


    /**
     * 功能：判断邮箱是否注册过
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     *完成时间：2023/1/2
     * */
    @PostMapping("/register/find")
    public ResultData ifEmailExist(@RequestBody EmailAccountVO vo){
        //UserService service = new UserServiceImpl();
        try{
            boolean exist = userService.findEmail(vo);
            if(exist){
                return ResultData.error(Constants.CODE_400,"邮箱已注册");
            }
            else
                return new ResultData(Constants.CODE_200,"邮箱未注册",null);
        }
        catch (Exception e) {
            System.out.println("异常信息"+e.getMessage());
            return  ResultData.sys_error();
        }
    }

    /**
     * 功能：注册用户
     * 状态：正在测试中
     * 负责人:Lmq
     * 新建时间：2022/12/28
     * 完成时间：2023/1/2
     * 注释：还需要规定userType
     * */
    @PostMapping("/register")
    public ResultData regist(@RequestBody RegistVO vo)
    {

        try{
            boolean isSuccess = userService.regist(vo);
            if(isSuccess)
                return new ResultData(Constants.CODE_200,"注册成功",null);
            else
                return new ResultData(Constants.CODE_400,"注册失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况:"+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：根据和userId修改密码
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/2
     * */
    @PostMapping("/findPassword")
    public ResultData alterPassword(@RequestBody FindPasswordVO vo)
    {
        try{
            boolean tmp = userService.alterPassword(vo);
            if(tmp)
                return new ResultData(Constants.CODE_200,"修改成功",null);
            else
                return new ResultData(Constants.CODE_400,"修改失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }

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
