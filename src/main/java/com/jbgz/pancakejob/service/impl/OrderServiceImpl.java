package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.*;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.jbgz.pancakejob.entity.Order;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.mapper.OrderMapper;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
        implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private JobMapper jobMapper;
    @Resource
    private JobhunterMapper jobhunterMapper;
    @Resource
    private UserMapper userMapper;

    //转换单个订单的DTO
    public OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderState(order.getOrderState());
        OrderJobDTO orderJobDTO = new OrderJobDTO();
        Job job = jobMapper.selectById(order.getJobId());
        orderJobDTO.setJobId(job.getJobId());       // 添加jobId 2023/6/18 lmq 跳转需求
        orderJobDTO.setWorkName(job.getWorkName());
        orderJobDTO.setWorkPlace(job.getWorkPlace());
        orderJobDTO.setStartTime(DateTimeTrans.datetimeToString(job.getStartTime()));
        orderJobDTO.setEndTime(DateTimeTrans.datetimeToString(job.getEndTime()));
        orderJobDTO.setWorkTime(job.getWorkTime());
        User user = userMapper.selectById(job.getRecruiterId());
        if (user.getScore() != null)
            orderJobDTO.setScore(user.getScore());
        else
            orderJobDTO.setScore(BigDecimal.ZERO);
        orderDTO.setJob(orderJobDTO);

        OrderScoreDTO orderScoreDTO = new OrderScoreDTO();
        orderScoreDTO.setOrderId(order.getOrderId());
        if (order.getRecruiterScore() == null)
            orderScoreDTO.setRecruiterScore(0);
        else
            orderScoreDTO.setRecruiterScore(order.getRecruiterScore());
        if (order.getJobhunterScore() == null)
            orderScoreDTO.setJobhunterScore(0);
        else
            orderScoreDTO.setJobhunterScore(order.getJobhunterScore());
        orderDTO.setOrderScore(orderScoreDTO);

        return orderDTO;
    }

    //转换求职者订单列表的DTO
    public List<OrderDTO> getOrderDTOList(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDTO orderDTO = getOrderDTO(order);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    //获取某兼职报名者的DTO
    public OrderAppliedDTO getOrderAppliedDTO(Order order) {
        OrderAppliedDTO orderAppliedDTO = new OrderAppliedDTO();
        orderAppliedDTO.setOrderId(order.getOrderId());
        orderAppliedDTO.setOrderState(order.getOrderState());
        orderAppliedDTO.setApplyDescription(order.getApplyDescription());
        Jobhunter jobhunter = jobhunterMapper.selectById(order.getJobhunterId());
        User user = userMapper.selectById(jobhunter.getJobhunterId());
        JobhunterDTO jobhunterDTO = new JobhunterDTO();
        jobhunterDTO.setJobhunterId(jobhunter.getJobhunterId());
        jobhunterDTO.setNickname(user.getNickname());
        jobhunterDTO.setHeadportrait(user.getHeadportrait());
        jobhunterDTO.setEmail(user.getEmail());
        jobhunterDTO.setSchool(jobhunter.getSchool());
        if (user.getScore() != null)
            jobhunterDTO.setScore(user.getScore());
        else
            jobhunterDTO.setScore(BigDecimal.ZERO);
        orderAppliedDTO.setJobhunter(jobhunterDTO);
        return orderAppliedDTO;
    }

    //转换某兼职的报名者列表的DTO
    public List<OrderAppliedDTO> getOrderAppliedDTOList(List<Order> orderList) {
        List<OrderAppliedDTO> orderAppliedDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderAppliedDTO orderAppliedDTO = getOrderAppliedDTO(order);
            orderAppliedDTOList.add(orderAppliedDTO);
        }
        return orderAppliedDTOList;
    }

    //获取某兼职录用人员的DTO
    public OrderAcceptedDTO getOrderAcceptedDTO(Order order) {
        OrderAcceptedDTO orderAcceptedDTO = new OrderAcceptedDTO();
        orderAcceptedDTO.setOrderId(order.getOrderId());
        orderAcceptedDTO.setOrderState(order.getOrderState());

        Jobhunter jobhunter = jobhunterMapper.selectById(order.getJobhunterId());
        User user = userMapper.selectById(jobhunter.getJobhunterId());
        JobhunterDTO jobhunterDTO = new JobhunterDTO();
        jobhunterDTO.setJobhunterId(jobhunter.getJobhunterId());
        jobhunterDTO.setHeadportrait(user.getHeadportrait());
        jobhunterDTO.setEmail(user.getEmail());
        jobhunterDTO.setSchool(jobhunter.getSchool());
        if (user.getScore() != null)
            jobhunterDTO.setScore(user.getScore());
        else
            jobhunterDTO.setScore(BigDecimal.ZERO);
        orderAcceptedDTO.setJobhunter(jobhunterDTO);

        OrderScoreDTO orderScoreDTO = new OrderScoreDTO();
        orderScoreDTO.setOrderId(order.getOrderId());
        if (order.getRecruiterScore() == null)
            orderScoreDTO.setRecruiterScore(0);
        else
            orderScoreDTO.setRecruiterScore(order.getRecruiterScore());
        if (order.getJobhunterScore() == null)
            orderScoreDTO.setJobhunterScore(0);
        else
            orderScoreDTO.setJobhunterScore(order.getJobhunterScore());
        orderAcceptedDTO.setOrderScore(orderScoreDTO);

        return orderAcceptedDTO;
    }

    //转换某兼职录用人员列表的DTO
    public List<OrderAcceptedDTO> getOrderAcceptedDTOList(List<Order> orderList) {
        List<OrderAcceptedDTO> orderAcceptedDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderAcceptedDTO orderAcceptedDTO = getOrderAcceptedDTO(order);
            orderAcceptedDTOList.add(orderAcceptedDTO);
        }
        return orderAcceptedDTOList;
    }

    //获取兼职报名状态
    public String getApplyState(int jobhunterId, int jobId) throws SelfDesignException{
        if(jobMapper.selectById(jobId) == null)
            throw new SelfDesignException("不存在该兼职信息");
        if(jobhunterMapper.selectById(jobhunterId) == null)
            throw new SelfDesignException("不存在该求职者");
        QueryWrapper<Order> orderWrapper = new QueryWrapper<Order>();
        orderWrapper.eq("jobhunter_id", jobhunterId).eq("job_id", jobId).orderByDesc("apply_time");
        List<Order> orders = orderMapper.selectList(orderWrapper);
        //orderWrapper.eq("jobhunter_id",jobhunterId).eq("job_id",jobId).eq("order_state","已报名");
        //int re=orderMapper.selectCount(orderWrapper);
        if (orders.size() == 0)
            return "未报名";
        else
            return orders.get(0).getOrderState();
    }

    //报名兼职
    public int createOrder(ApplyJobVO applyJobVO) throws SelfDesignException {
        if(applyJobVO.getApplyReason() == null)
            throw new SelfDesignException("申请理由为空");
        Job job = jobMapper.selectById(applyJobVO.getJobId());
        if (job == null)
            throw new SelfDesignException("不存在该兼职信息");
        else if (!job.getJobState().equals("已通过"))
            throw new SelfDesignException("该兼职当前未在招聘");
        if (jobhunterMapper.selectById(applyJobVO.getJobhunterId()) == null)
            throw new SelfDesignException("不存在该求职者");
        QueryWrapper<Order> orderWrapper = new QueryWrapper<>();
        orderWrapper.eq("jobhunter_id", applyJobVO.getJobhunterId())
                .eq("job_id", applyJobVO.getJobId())
                .and(i -> i.eq("order_state", "已报名").or()
                        .eq("order_state", "已通过").or()
                        .eq("order_state", "已录用").or()
                        .eq("order_state", "支付异常"));
        if (orderMapper.selectCount(orderWrapper) > 0)
            throw new SelfDesignException("该求职者已报名该兼职");
        Order order = new Order();
        order.setJobhunterId(applyJobVO.getJobhunterId());
        order.setJobId(applyJobVO.getJobId());
        order.setApplyTime(new Date());
        order.setApplyDescription(applyJobVO.getApplyReason());
        order.setOrderState("已报名");
        if (orderMapper.insert(order) > 0) {
            //兼职的已报名人数+1
            job.setAppliedNum(job.getAppliedNum() + 1);
            jobMapper.updateById(job);
            return order.getOrderId();
        } else
            return -1;
    }

    //取消报名
    public boolean cancelOrder(int orderId) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        order.setOrderState("已取消");
        int re = orderMapper.updateById(order);
        if (re > 0) {
            //兼职的已报名人数-1
            Job job = jobMapper.selectById(order.getJobId());
            job.setAppliedNum(job.getAppliedNum() - 1);
            re = jobMapper.updateById(job);
        }
        return re > 0;
    }

    //获取求职者的订单列表
    public List<OrderDTO> getOrderList(int jobhunterId) throws SelfDesignException {
        if (jobhunterMapper.selectById(jobhunterId) == null)
            throw new SelfDesignException("不存在该求职者");
        QueryWrapper<Order> orderWrapper = new QueryWrapper<Order>();
        orderWrapper.eq("jobhunter_id", jobhunterId);
        orderWrapper.orderByDesc("apply_time");
        List<OrderDTO> orderDTOList = getOrderDTOList(orderMapper.selectList(orderWrapper));
        return orderDTOList;
    }

    //获取某个兼职的已报名订单列表（除了已取消的所有状态）
    public List<OrderAppliedDTO> getOrderAppliedList(int jobId) throws SelfDesignException {
        if (jobMapper.selectById(jobId) == null)
            throw new SelfDesignException("不存在该兼职信息");
        QueryWrapper<Order> orderWrapper = new QueryWrapper<Order>();
        orderWrapper.eq("job_id", jobId).and(i -> i
                .eq("order_state", "已报名").or()
                .eq("order_state", "已通过").or()
                .eq("order_state", "未通过").or()
                .eq("order_state", "已录用").or()
                .eq("order_state", "已放弃").or()
                .eq("order_state", "已完成").or()
                .eq("order_state", "支付异常"));
        orderWrapper.orderByDesc("apply_time");
        List<OrderAppliedDTO> orderAppliedDTOList = getOrderAppliedDTOList(orderMapper.selectList(orderWrapper));
        return orderAppliedDTOList;
    }

    //获取某个兼职的录用名单(已录用、已完成、支付异常)
    public List<OrderAcceptedDTO> getOrderAcceptedList(int jobId) throws SelfDesignException {
        if (jobMapper.selectById(jobId) == null)
            throw new SelfDesignException("不存在该兼职信息");
        QueryWrapper<Order> orderWrapper = new QueryWrapper<Order>();
        orderWrapper.eq("job_id", jobId).and(i -> i
                .eq("order_state", "已录用").or()
                .eq("order_state", "已完成").or()
                .eq("order_state", "支付异常"));
        orderWrapper.orderByDesc("pass_time");
        List<OrderAcceptedDTO> orderAcceptedDTOList = getOrderAcceptedDTOList(orderMapper.selectList(orderWrapper));
        return orderAcceptedDTOList;
    }

    //修改订单状态
    public boolean changeOrderState(int orderId, String newState) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        if (newState == null)
            throw new SelfDesignException("订单状态不能为空");
        order.setOrderState(newState);
        int re = orderMapper.updateById(order);
        return re > 0;
    }

    //修改订单评分
    public boolean changeOrderScore(int orderId, int newScore, String scoreType) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        else if (!order.getOrderState().equals("已完成"))
            throw new SelfDesignException("订单还未完成");
        if (newScore < 1 || newScore > 5)
            throw new SelfDesignException("评分分数不在1-5区间");
        DecimalFormat dec = new DecimalFormat("0.00");
        int re1, re2;
        if(scoreType == null)
            throw new SelfDesignException("评价类型为空");
        else if (scoreType.equals("jobhunter")) {
            order.setJobhunterScore(newScore);
            re1 = orderMapper.updateById(order);
            double avgScore = orderMapper.averageJobhunterScore(order.getJobhunterId());
            User user = new User();
            user.setUserId(order.getJobhunterId());
            user.setScore(new BigDecimal(dec.format(avgScore)));
            re2 = userMapper.updateById(user);
            System.out.println("求职者平均分数：" + avgScore);
        } else if (scoreType.equals("recruiter")) {
            order.setRecruiterScore(newScore);
            re1 = orderMapper.updateById(order);
            Job job = jobMapper.selectById(order.getJobId());
            double avgScore = orderMapper.selectRecruiterOrder(job.getRecruiterId());
            User user = new User();
            user.setUserId(job.getRecruiterId());
            user.setScore(new BigDecimal(dec.format(avgScore)));
            re2 = userMapper.updateById(user);
            System.out.println("招聘方平均分数：" + avgScore);
        } else
            throw new SelfDesignException("评价类型错误");

        return re1 > 0 && re2 > 0;
    }

    //招聘方发放offer
    public boolean sendOfferOrNot(int orderId, boolean send) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        else if (!order.getOrderState().equals("已报名"))
            throw new SelfDesignException("此订单非已报名状态");
        if (send) {
            order.setPassTime(new Date());
            orderMapper.updateById(order);
            return changeOrderState(orderId, "已通过");
        } else
            return changeOrderState(orderId, "未通过");
    }

    //求职者确认是否接受录用
    public boolean acceptOfferOrNot(int orderId, boolean accept) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        else if (!order.getOrderState().equals("已通过"))
            throw new SelfDesignException("此订单非已通过状态");
        if (accept) {
            changeOrderState(orderId, "已录用");
            Job job = jobMapper.selectById(order.getJobId());
            job.setAcceptedNum(job.getAcceptedNum() + 1);
            int re = jobMapper.updateById(job);
            return re > 0;
        } else
            return changeOrderState(orderId, "已放弃");
    }

    //招聘方确认工作完成
    public boolean finishOrder(int orderId) throws SelfDesignException {
        Order order = orderMapper.selectById(orderId);
        if (order == null)
            throw new SelfDesignException("订单不存在");
        changeOrderState(orderId, "已完成");
        Job job = jobMapper.selectById(order.getJobId());
        job.setFinishedNum(job.getFinishedNum() + 1);
        //如果所有工作订单均已完成且已结束招聘，将兼职状态修改为已完成
        if (job.getFinishedNum().equals(job.getAcceptedNum()) && job.getJobState().equals("已结束"))
            job.setJobState("已完成");
        int re = jobMapper.updateById(job);
        return re > 0;
    }

    //招聘方结束招聘时将剩余报名者的订单状态修改为“未通过”，返回需要发送未通过通知的求职者名单
    public List<Integer> refuseRestJobhunter(int jobId) {
        //筛选出剩余的报名者
        QueryWrapper<Order> orderWrapper = new QueryWrapper<Order>();
        orderWrapper.eq("job_id", jobId).eq("order_state", "已报名");
        List<Order> orders = orderMapper.selectList(orderWrapper);
        List<Integer> refuseList = new ArrayList<>();
        orders.forEach(order -> {
            order.setOrderState("未通过");
            refuseList.add(order.getOrderId());//获得需要发送未通过通知的订单列表
            orderMapper.updateById(order);
        });
//        System.out.println("订单列表："+ refuseList);
        return refuseList;
    }
}




