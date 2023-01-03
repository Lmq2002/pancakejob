package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.ApplyJobVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【order】的数据库操作Service
* @createDate 2022-12-30 22:39:39
*/
public interface OrderService extends IService<Order> {

    int createOrder(ApplyJobVO applyJobVO);

    boolean cancelOrder(int orderId);

    boolean changeOrderState(int orderId,String newState);

    boolean changeOrderScore(int orderId,int newScore,String scoreType);
    boolean sendOfferOrNot(int orderId,boolean send);

    boolean acceptOfferOrNot(int orderId,boolean accept);

    boolean finishOrder(int orderId);

    List<Integer> refuseRestJobhunter(int jobId);

}
