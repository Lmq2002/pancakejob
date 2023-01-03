package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.service.JobTypeService;
import com.jbgz.pancakejob.utils.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private JobTypeService jobTypeService;

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
}
