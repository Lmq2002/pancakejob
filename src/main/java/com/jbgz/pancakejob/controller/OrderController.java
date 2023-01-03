package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.dto.OrderAcceptedDTO;
import com.jbgz.pancakejob.dto.OrderAppliedDTO;
import com.jbgz.pancakejob.dto.OrderDTO;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    //报名兼职
    @PostMapping("/applyForJob")
    public ResultData applyForJob(@RequestBody ApplyJobVO applyJobVO){
        try{
            ResultData result=new ResultData();
            int orderId=orderService.createOrder(applyJobVO);
            result.code=200;
            result.message="报名成功";
            result.data.put("orderId：",orderId);
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //取消报名
    @PutMapping("/cancelApplyForJob")
    public ResultData cancelApplyForJob(int orderId){
        try{
            ResultData result=new ResultData();
            boolean re=orderService.cancelOrder(orderId);
            result.code=200;
            if(re)
                result.message="取消报名成功";
            else
                result.message="取消报名失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }

    }

    //获取求职者的订单列表
    @GetMapping("/getJobhunterOrderList")
    public ResultData getJobhunterOrderList(int jobhunterId){
        try{
            ResultData result=new ResultData();
            List<OrderDTO> order_list=orderService.getOrderList(jobhunterId);
            result.data.put("order_list",order_list);
            result.code=200;
            result.message="请求成功";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //获取某兼职报名者列表
    @GetMapping("/getAppliedList")
    public ResultData getAppliedList(int jobId){
        try{
            ResultData result=new ResultData();
            List<OrderAppliedDTO> order_list=orderService.getOrderAppliedList(jobId);
            result.data.put("order_list",order_list);
            result.code=200;
            result.message="请求成功";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //获取某兼职录用者列表
    @GetMapping("/getAcceptedList")
    public ResultData getAcceptedList(int jobId){
        try{
            ResultData result=new ResultData();
            List<OrderAcceptedDTO> order_list=orderService.getOrderAcceptedList(jobId);
            result.data.put("order_list",order_list);
            result.code=200;
            result.message="请求成功";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //求职者确认录用结果
    @PutMapping("/confirmPassResult")
    public ResultData confirmPassResult(int orderId,String orderState){
        try{
            ResultData result=new ResultData();
            boolean accept=orderState.equals("已录用");
            boolean re=orderService.acceptOfferOrNot(orderId,accept);

            /*向招聘方发送通知?*/

            result.code=200;
            if(re)
                result.message="修改成功";
            else
                result.message="修改失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }

    }

    //招聘方确认是否通过
    @PutMapping("/giveOffer")
    public ResultData giveOffer(int orderId,String orderState){
        try{
            ResultData result=new ResultData();
            boolean send=orderState.equals("已通过");
            boolean re=orderService.sendOfferOrNot(orderId,send);

            /*NotificationService向求职者发送通知*/

            result.code=200;
            if(re)
                result.message="修改成功";
            else
                result.message="修改失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //确认求职者完成工作
    @PutMapping("/confirmJobFinish")
    public ResultData confirmJobFinish(int orderId){
        try{
            ResultData result=new ResultData();
            boolean re=orderService.finishOrder(orderId);
            result.code=200;
            if(re)
                result.message="确认成功";
            else
                result.message="确认失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //评价求职者
    @PostMapping("/commentOnJobhunter")
    public ResultData commentOnJobhunter(int orderId,int jobhunterScore){
        try{
            ResultData result=new ResultData();
            boolean re=orderService.changeOrderScore(orderId,jobhunterScore,"jobhunter");
            result.code=200;
            if(re)
                result.message="评分成功";
            else
                result.message="评分失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

    //评价招聘方
    @PostMapping("/commentOnRecruiter")
    public ResultData commentOnRecruiter(int orderId,int recruiterScore){
        try{
            ResultData result=new ResultData();
            boolean re=orderService.changeOrderScore(orderId,recruiterScore,"recruiter");
            result.code=200;
            if(re)
                result.message="评分成功";
            else
                result.message="评分失败";
            return result;
        }
        catch (Exception e){
            return ResultData.error();
        }
    }

}
