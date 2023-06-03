package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.service.ReportService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.JobUpVO;
import com.jbgz.pancakejob.vo.ReportVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.module.Configuration;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;
    @Resource
    private OrderService orderService;
    @Resource
    private ReportService reportService;
    @Resource
    private NotificationService notificationService;

    //获取所有审核通过的兼职列表
    @GetMapping("/getJobList")
    public ResultData getJobList(int jobId) {
        String state = "已通过";
        if (jobId == -1) {//获取整个兼职列表
            try {
                ResultData result = new ResultData();
                result.data.put("job_list", jobService.getJobList(state));
                result.message = "请求成功";
                result.code = Constants.CODE_200;
                //System.out.println(result);//打印观察
                return result;
            } catch (Exception e) {
                System.out.println("错误信息：" + e.getMessage());
                return ResultData.error(Constants.CODE_400, e.getMessage());
            }
        } else {//获取单个兼职信息
            try {
                ResultData result = new ResultData();
                result.data.put("job_list", jobService.getJobInfo(jobId));
                result.message = "请求成功";
                result.code = Constants.CODE_200;
                //System.out.println(result);//打印观察
                return result;
            } catch (Exception e) {
                System.out.println("错误信息：" + e.getMessage());
                return ResultData.error(Constants.CODE_400, e.getMessage());
            }
        }

    }

    //获取所有招聘方管理的兼职列表
    @GetMapping("/getAllJobList")
    public ResultData getAllJobList(int recruiterId) {
        try {
            ResultData result = new ResultData();
            result.data.put("job_list", jobService.getAllJobList(recruiterId));
            result.message = "请求成功";
            result.code = Constants.CODE_200;
            System.out.println(result);//打印观察
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //发布兼职or存草稿
    @PostMapping("/upJob")
    public ResultData upJob(@RequestBody JobUpVO jobVO) {
        try {
            ResultData result = new ResultData();
            boolean re = jobService.createJob(jobVO);
            if (re) {
                result.message = "发布成功";
                result.code = Constants.CODE_200;
            } else {
                result.message = "发布失败";
                result.code = Constants.CODE_400;
            }
            System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //结束招聘
    @PutMapping("/closeRecruit")
    public ResultData closeRecruit(int jobId) {
        try {
            ResultData result = new ResultData();
            jobService.closeRecruit(jobId);
            //修改剩余报名者的结果为未通过
            List<Integer> refuses = orderService.refuseRestJobhunter(jobId);
            //向求职者发送信息
            boolean re = notificationService.noticeRestJobhunter(refuses);
            //调用NotificationService的方法
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "已结束招聘";
            } else {
                result.code = Constants.CODE_400;
                result.message = "结束招聘失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //举报兼职
    @PostMapping("/reportJob")
    public ResultData reportJob(@RequestBody ReportVO reportVO) {
        try {
            ResultData result = new ResultData();
            boolean re = reportService.createReport(reportVO);
            if (re) {
                result.message = "举报成功";
                result.code = Constants.CODE_200;
            } else {
                result.message = "举报失败";
                result.code = Constants.CODE_400;
            }
            //System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }
}
