package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.JobUpVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;

    //获取所有审核通过的兼职列表
    @GetMapping("/getJobList")
    public ResultData getJobList(int jobId){
        String state="已通过";
        if(jobId==-1) {//获取整个兼职列表
            try{
                ResultData result=new ResultData();
                result.data.put("job_list",jobService.getJobList(state));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            }
            catch(Exception e){
                System.out.println("异常信息"+e.getMessage());
                return ResultData.error();
            }
        }
        else  {//获取单个兼职信息
            try{
                ResultData result=new ResultData();
                result.data.put("job_list",jobService.getJobInfo(jobId));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            }
            catch(Exception e){
                return ResultData.error();
            }
        }

    }

    //获取所有待审核的兼职列表
    @GetMapping("/getJobUnreviewed")
    public ResultData getJobUnreviewed(int jobId){
        String state="未审核";
        if(jobId==-1) {//获取整个兼职列表
            try{
                ResultData result=new ResultData();
                result.data.put("job_list",jobService.getJobList(state));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            }
            catch(Exception e){
                System.out.println("异常信息"+e.getMessage());
                return ResultData.error();
            }
        }
        else  {//获取单个兼职信息
            try{
                ResultData result=new ResultData();
                result.data.put("job_list",jobService.getJobInfo(jobId));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            }
            catch(Exception e){
                return ResultData.error();
            }
        }
    }

    //发布兼职
    @PostMapping("/upJob")
    public ResultData upJob(@RequestBody JobUpVO jobVO){
        ResultData result=new ResultData();

        return result;
    }
}
