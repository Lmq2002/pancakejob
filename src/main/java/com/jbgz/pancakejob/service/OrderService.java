package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.OrderAcceptedDTO;
import com.jbgz.pancakejob.dto.OrderAppliedDTO;
import com.jbgz.pancakejob.dto.OrderDTO;
import com.jbgz.pancakejob.dto.RecruiterOrderDTO;
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
    OrderDTO getOrderDTO(Order order);
    List<OrderDTO> getOrderDTOList(List<Order> orderList);
    List<OrderDTO> getOrderList(int jobhunterId);

    OrderAppliedDTO getOrderAppliedDTO(Order order);
    List<OrderAppliedDTO> getOrderAppliedDTOList(List<Order> orderList);
    List<OrderAppliedDTO> getOrderAppliedList(int jobId);

    OrderAcceptedDTO getOrderAcceptedDTO(Order order);
    List<OrderAcceptedDTO> getOrderAcceptedDTOList(List<Order> orderList);
    List<OrderAcceptedDTO> getOrderAcceptedList(int jobId);

    String getApplyState(int jobhunterId,int jobId);
    int createOrder(ApplyJobVO applyJobVO);
    boolean cancelOrder(int orderId);
    boolean changeOrderState(int orderId,String newState);
    boolean changeOrderScore(int orderId, int newScore, String scoreType);
    boolean sendOfferOrNot(int orderId,boolean send);
    boolean acceptOfferOrNot(int orderId,boolean accept);
    boolean finishOrder(int orderId);
    List<Integer> refuseRestJobhunter(int jobId);
}
