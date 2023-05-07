package com.jbgz.pancakejob.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.CompanyAuthenDTO;
import com.jbgz.pancakejob.dto.PersonAuthenDTO;
import com.jbgz.pancakejob.service.CompanyAuthenticationService;
import com.jbgz.pancakejob.service.JobTypeService;
import com.jbgz.pancakejob.service.RealnameAuthenticationService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.AuditVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private JobTypeService jobTypeService;

    @Resource
    private RealnameAuthenticationService realnameAuthenticationService;

    @Resource
    private CompanyAuthenticationService companyAuthenticationService;

    //获取所有兼职类型
    @GetMapping("/getJobTypeList")
    public ResultData getJobTypeList(){
        try{
            ResultData result=new ResultData();
            result.data.put("jobtype_list",jobTypeService.getTypeList());
            result.code=200;
            result.message="请求成功";
            System.out.println("result:"+result);
            return result;
        }
        catch(Exception e){
            System.out.println("错误信息"+e.getMessage());
            return ResultData.error();
        }
    }

    //增加兼职类型
    @PostMapping("/addType")
    public ResultData addType(String typeName){
        try{
            ResultData result=new ResultData();
            int re=jobTypeService.addJobType(typeName);
            result.code=200;
            if(re==-1)
                result.message="兼职类型已存在";
            else if(re==0)
                result.message="添加失败";
            else
                result.message="添加成功";
            System.out.println("result:"+result);
            return result;
        }
        catch (Exception e){
            System.out.println("错误信息"+e.getMessage());
            return ResultData.error();
        }
    }


    //删除兼职类型
    @DeleteMapping("/deleteType")
    public ResultData deleteType(int typeId){
        try{
            ResultData result=new ResultData();
            boolean re=jobTypeService.deleteJobType(typeId);
            result.code=200;
            if(re)
                result.message="删除成功";
            else
                result.message="删除失败";
            System.out.println("result:"+result);
            return result;
        }
        catch(Exception e){
            System.out.println("错误信息"+e.getMessage());
            return ResultData.error();
        }
    }


    //修改兼职类型
    @PutMapping("/changeType")
    public ResultData changeType(int typeId,String typeName){
        try{
            ResultData result=new ResultData();
            int re=jobTypeService.changeJobType(typeId,typeName);
            result.code=200;
            if(re==-1)
                result.message="兼职类型已存在";
            else if(re==0)
                result.message="修改失败";
            else
                result.message="修改成功";
            System.out.println("result:"+result);
            return result;
        }
        catch (Exception e){
            System.out.println("错误信息"+e.getMessage());
            return ResultData.error();
        }
    }


    /**
     * 功能：审查求职者实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/auditUserAuthentication")
    public ResultData auditUserAuthentication(@RequestBody AuditVO vo){
        try{
            boolean tmp = realnameAuthenticationService.auditAuthentication(vo);
            if(tmp) return new ResultData(Constants.CODE_200,"成功",null);
            return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }


    /**
     * 功能：获取求职者实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @GetMapping("/getUserAuthenticationList")
    public ResultData getUserAuthenticationList(@RequestParam(value = "jobhunterId",required = false)Integer jobhunterId){
        try {
            List<PersonAuthenDTO> list = realnameAuthenticationService.getAuthenList(jobhunterId);
            if(list!=null){
                ResultData result=new ResultData();
                result.data.put("personauthen_list",list);
                result.code=Constants.CODE_200;
                result.message = "成功";
                return result;
            }
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }

    }


    /**
     * 功能：审查招聘方企业申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/auditCompanyAuthentication")
    public ResultData auditCompanyAuthentication(@RequestBody AuditVO vo){
        try{
            boolean tmp = companyAuthenticationService.auditAuthentication(vo);
            if(tmp) return new ResultData(Constants.CODE_200,"成功",null);
            return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取企业实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @GetMapping("/getCompanyAuthenticationList")
    public ResultData getCompanyAuthenticationList(@RequestParam(value = "recruiterId",required = false)Integer recruiterId){
        try {
            List<CompanyAuthenDTO> list = companyAuthenticationService.getAuthenList(recruiterId);
            if(list!=null){
                ResultData result=new ResultData();
                result.data.put("companyauthen_list",list);
                result.code=Constants.CODE_200;
                result.message = "成功";
                return result;
            }
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况："+e.getMessage());
            return ResultData.sys_error();
        }
    }

}

