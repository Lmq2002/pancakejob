package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Order;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-12-30 22:39:39
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




