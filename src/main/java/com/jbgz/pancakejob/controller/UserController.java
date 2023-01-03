package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.common.RealNameStatus;
import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.dto.RecruiterPersonInfoDTO;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.jbgz.pancakejob.entity.RealnameAuthentication;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.*;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JobhunterService jobhunterService;
    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private RealnameAuthenticationService realnameAuthenticationService;
    @Autowired
    private CompanyAuthenticationService companyAuthenticationService;

    @Autowired
    private FavoritesDirService favoritesDirService;

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
     * 状态：正在测试中中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/3
     * */
    @GetMapping("/contactMethod/get")
    public ResultData getContactMethod(@RequestParam("userId") Integer userId){
        try{
            User user = userService.getById(userId);
            if(user!=null){
                ResultData result = new ResultData();
                result.code = Constants.CODE_200;
                result.message = "存在联系方式";
                result.data.put("contactMethod",user.getContactMethod());
                return result;
            }
            else return new ResultData(Constants.CODE_400,"不存在联系方式",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加用户联系方式
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/3
     * */
    @PostMapping("/contactMethod/set")
    public ResultData setContactMethod(@RequestBody ContactMethodVO vo){
        try {
            boolean tmp = userService.setContactMethod(vo);
            if(tmp)
                return new ResultData(Constants.CODE_200,"成功",null);
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e)
        {
            System.out.println("异常情况:"+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加求职者实名认证
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     * */
    @PutMapping ("/Jobhunter/authentication")
    public ResultData addJobhunterAuthentication(@RequestBody GetJobhunterAuthenticationVO vo){
        try{
            RealnameAuthentication authentication = new RealnameAuthentication();
            authentication.setJobhunterId(vo.getJobhunterId());
            authentication.setIdcard(vo.getIdcard());
            authentication.setIdentification(vo.getIdentification());
            authentication.setRealName(vo.getRealName());
            authentication.setApplyTime(new Date());
            authentication.setCheckStatus(RealNameStatus.WAITING);
            if(realnameAuthenticationService.save(authentication))
                return new ResultData(Constants.CODE_200,"成功",null);
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况:"+e.getMessage());
            return ResultData.sys_error();
        }

    }

    /**
     * 功能：添加企业认证
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     * */
    @PutMapping("recruiter/authentication")
    public ResultData addRecruiterAuthentication(@RequestBody GetRecruiterAuthenticationVO vo){
        try{
            CompanyAuthentication authentication = new CompanyAuthentication();
            authentication.setRecruiterId(vo.getRecruiterId());
            authentication.setCompanyName(vo.getCompanyName());
            authentication.setCompanyId(vo.getCompanyId());
            authentication.setCompanyType(vo.getCompanyType());
            authentication.setCertification(vo.getCertification());
            authentication.setApplyTime(new Date());
            authentication.setCheckStatus(RealNameStatus.WAITING);
            if(companyAuthenticationService.save(authentication))
                return new ResultData(Constants.CODE_200,"成功",null);
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况:"+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取企业信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     * */
    @GetMapping("/recruiter/personInfo/get")
    public ResultData getRecruiterPersonInfo(@RequestParam("recruiterId") Integer recruiterId){
        try{
            RecruiterPersonInfoDTO dto = recruiterService.getInfo(recruiterId);
            if(dto==null)
                return new ResultData(Constants.CODE_400,"失败",null);
            ResultData result = new ResultData();
            result.code = Constants.CODE_200;
            result.message = "成功";
            result.data.put("company_list",dto);
            return result;
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：设置企业信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     * */
    @PutMapping("/recruiter/personInfo/set")
    public ResultData setRecruiterPersonInfo(@RequestBody RecruiterPersonInfoVO vo){
        try{
            boolean tmp = recruiterService.setInfo(vo);
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
     * 功能：通过jobhunterid获取收藏夹列表
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/4
     * */
    @GetMapping("/jobhunter/favorites")
    public ResultData getFavoritesDirList(@RequestParam("jobhunterId") Integer jobhunterId){
        try{
            ResultData result= new ResultData();
            result.code = Constants.CODE_200;
            result.message = "成功";
            result.data.put("favorites_list",favoritesDirService.getDirList(jobhunterId));
            return result;
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

}
