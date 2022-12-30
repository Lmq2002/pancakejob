package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Notification;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【notification】的数据库操作Service实现
* @createDate 2022-12-30 22:39:35
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

}




