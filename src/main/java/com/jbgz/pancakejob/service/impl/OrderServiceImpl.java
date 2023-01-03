package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.Order;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.mapper.OrderMapper;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-12-30 22:39:39
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private JobMapper jobMapper;

    //报名兼职
    public int createOrder(ApplyJobVO applyJobVO){
//        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<Order>();
//        orderQueryWrapper.eq("jobhunter_id",applyJobVO.getJobhunterId())
//                .eq("job_id",applyJobVO.getJobId())
//                .eq("order_state","已取消");
//        Order order=orderMapper.selectOne(orderQueryWrapper);
//        //如果有已取消的订单记录，则直接修改为已报名
//        if(order!=null){
//            order.setOrderState("已报名");
//            orderMapper.updateById(order);
//            return order.getOrderId();
//        }//没有就插入新的订单记录
//        else{
        //兼职的已报名人数+1
        Job job=jobMapper.selectById(applyJobVO.getJobId());
        job.setAppliedNum(job.getAppliedNum()+1);
        jobMapper.updateById(job);
        Order order=new Order();
            order.setJobhunterId(applyJobVO.getJobhunterId());
            order.setJobId(applyJobVO.getJobId());
            order.setApplyTime(new Date());
            order.setApplyDiscription(applyJobVO.getApplyReason());
            order.setOrderState("已报名");
            int re=orderMapper.insert(order);
            return re;
//        }
    }

    //取消报名
    public boolean cancelOrder(int orderId){
        Order order=orderMapper.selectById(orderId);
        //兼职的已报名人数-1
        Job job=jobMapper.selectById(order.getJobId());
        job.setAppliedNum(job.getAppliedNum()-1);
        jobMapper.updateById(job);
        order.setOrderState("已取消");
        orderMapper.updateById(order);
        return false;
    }

    //修改订单状态
    public boolean changeOrderState(int orderId,String newState){
        Order order=new Order();
        order.setOrderId(orderId);
        order.setOrderState(newState);
        int re=orderMapper.updateById(order);
        return re>0;
    }

    //修改订单评分
    public boolean changeOrderScore(int orderId,int newScore,String scoreType){
        Order order=new Order();
        order.setOrderId(orderId);
        if(scoreType.equals("jobhunter"))
            order.setJobhunterScore(newScore);
        else if(scoreType.equals("recuriter"))
            order.setRecuriterScore(newScore);
        int re=orderMapper.updateById(order);
        return re>0;
    }

    //招聘方发放offer
    public boolean sendOfferOrNot(int orderId,boolean send){
        if(send)
            return changeOrderState(orderId,"已通过");
        else
            return changeOrderState(orderId,"未通过");
    }

    //求职者确认是否接受录用
    public boolean acceptOfferOrNot(int orderId,boolean accept){
        if(accept){
            changeOrderState(orderId,"已录用");
            Order order=orderMapper.selectById(orderId);
            Job job=jobMapper.selectById(order.getJobId());
            job.setAcceptedNum(job.getAcceptedNum()+1);
            int re=jobMapper.updateById(job);
            return re>0;
        }
        else
            return changeOrderState(orderId,"未录用");
    }

    //招聘方确认工作完成
    public boolean finishOrder(int orderId){
        changeOrderState(orderId,"已完成");
        Order order=orderMapper.selectById(orderId);
        Job job=jobMapper.selectById(order.getJobId());
        job.setFinishedNum(job.getFinishedNum()+1);
        //如果所有工作订单均已完成，将兼职状态修改为已完成
        if(job.getFinishedNum().equals(job.getAcceptedNum()))
            job.setJobState("已完成");
        int re=jobMapper.updateById(job);
        return re>0;
    }

    //招聘方结束招聘时将剩余报名者的订单状态修改为“未通过”，返回需要发送未通过通知的求职者名单
    public List<Integer> refuseRestJobhunter(int jobId){
        //筛选出剩余的报名者
        QueryWrapper<Order> orderWrapper=new QueryWrapper<Order>();
        orderWrapper.eq("job_id",jobId).eq("order_state","已报名");
        List<Order> orders=orderMapper.selectList(orderWrapper);
        List<Integer> refuseList=new ArrayList<>();
        orders.forEach(order -> {
            order.setOrderState("未通过");
            refuseList.add(order.getJobhunterId());//获得需要发送未通过通知的人员列表
            orderMapper.updateById(order);
        });
        return refuseList;
    }

}




