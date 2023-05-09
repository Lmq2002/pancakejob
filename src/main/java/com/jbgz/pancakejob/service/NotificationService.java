package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.NotificationDTO;
import com.jbgz.pancakejob.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.entity.Order;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【notification】的数据库操作Service
* @createDate 2022-12-30 22:39:35
*/
public interface NotificationService extends IService<Notification> {

    List<NotificationDTO> getNotificationList(int userId);

    boolean addNotification(int order_id, String notificationType);

    boolean noticeRestJobhunter(List<Integer> order_list);

    boolean deleteNotification(int notificationId);

}
