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
            return ResultData.error();
        }
    }

    //增加兼职类型
    @PostMapping("/addType")
    public ResultData addType(String typeName){
        try{
            ResultData result=new ResultData();
            boolean re=jobTypeService.addJobType(typeName);
            if(re){
                result.message="添加成功";
                result.code=200;
            }
            else{
                result.message="添加失败";
                result.code=200;
            }
            System.out.println("result:"+result);
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }


    //删除兼职类型
    @DeleteMapping("/deleteType")
    public ResultData deleteType(int typeId){
        try{
            ResultData result=new ResultData();
            boolean re=jobTypeService.deleteJobType(typeId);
            if(re){
                result.message="删除成功";
                result.code=200;
            }
            else{
                result.message="删除失败";
                result.code=200;
            }
            System.out.println("result:"+result);
            return result;
        }
        catch(Exception e){
            return ResultData.error();
        }
    }


    //修改兼职类型
    @PutMapping("/changeType")
    public ResultData changeType(int typeId,String typeName){
        try{
            ResultData result=new ResultData();
            boolean re=jobTypeService.changeJobType(typeId,typeName);
            if(re){
                result.message="修改成功";
                result.code=200;
            }
            else{
                result.message="修改失败";
                result.code=200;
            }
            System.out.println("result:"+result);
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }
}
