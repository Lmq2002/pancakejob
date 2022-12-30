package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.utils.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;

    //获取所有的兼职列表
    @GetMapping("/showJobList")
    public ResultData showJobList(int jobId){
        if(jobId==-1) {//获取整个兼职列表
            try{
                ResultData resultData = jobService.getJobList();
                System.out.println(resultData);//打印观察
                return resultData;
            }
            catch(Exception e){
                System.out.println("异常信息"+e.getMessage());
                return ResultData.error();
            }
        }
        else  {//获取单个兼职信息
            try{
                ResultData resultData = jobService.getJobInfo(jobId);
                System.out.println(resultData);//打印观察
                return resultData;
            }
            catch(Exception e){
                return ResultData.error();
            }
        }

    }
}
