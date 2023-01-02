package com.jbgz.pancakejob.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.service.JobhunterService;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.GetPersonalInfoVO;
import com.jbgz.pancakejob.vo.PersonalInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JobhunterService jobhunterService;

    /**
     * 功能：获取个人信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     *完成时间：2023/1/2
     */
    @GetMapping("/Jobhunter/personInfo/get")
    public ResultData getPersonalInfo(@RequestParam("jobhunterId") Integer id){
        try{
            GetPersonalInfoVO vo = new GetPersonalInfoVO();
            vo.setJobhunterId(id);
            PersonalInfoDTO data = jobhunterService.getPersonalInfo(vo);
            ResultData result = new ResultData();
            if(data!=null)
            {
                result.code = Constants.CODE_200;
                result.message = "成功";
                result.data.put("person_list",data);
                return result;
            }
            else
                return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e)
        {
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     *功能：设置个人信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/2
     */
    @PutMapping("/Jobhunter/personInfo/set")
    public ResultData setPersonalInfo(@RequestBody PersonalInfoVO vo){
        try{
            boolean tmp = jobhunterService.setPersonalInfo(vo);
            if(tmp)
                return new ResultData(Constants.CODE_200,"成功",null);
            else
                return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取用户联系方式
     * 状态：正在开发中
     * 负责人：lmq
     * 新建时间：2023/1/2
     *
     * */

    /**
     * 功能：添加用户联系方式
     * 状态：正在开发中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * */

    /**
     * 功能：添加实名认证
     * */
}
