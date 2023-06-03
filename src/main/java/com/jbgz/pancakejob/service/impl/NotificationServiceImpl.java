package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.common.NotificationType;
import com.jbgz.pancakejob.common.UserType;
import com.jbgz.pancakejob.dto.NotificationDTO;
import com.jbgz.pancakejob.entity.*;
import com.jbgz.pancakejob.mapper.*;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.utils.SelfDesignException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CSY0214
 * @description 针对表【notification】的数据库操作Service实现
 * @createDate 2022-12-30 22:39:35
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
        implements NotificationService {

    @Resource
    private NotificationMapper notificationMapper;
    @Resource
    private JobMapper jobMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;

    NotificationDTO getNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setTitle(notification.getTitle());
        notificationDTO.setContent(notification.getContent());
        notificationDTO.setSendTime(DateTimeTrans.datetimeToString(notification.getSendTime()));
        return notificationDTO;
    }

    List<NotificationDTO> getNotificationListDTO(List<Notification> notificationList) {
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = getNotificationDTO(notification);
            notificationDTOList.add(notificationDTO);
        }
        return notificationDTOList;
    }

    public List<NotificationDTO> getNotificationList(int userId) throws SelfDesignException {
        if(userMapper.selectById(userId)==null)
            throw new SelfDesignException("不存在该用户");
        QueryWrapper<Notification> notificationWrapper = new QueryWrapper<>();
        notificationWrapper.eq("user_id", userId);
        notificationWrapper.orderByDesc("send_time");
        List<NotificationDTO> notificationDTOList = getNotificationListDTO(notificationMapper.selectList(notificationWrapper));
        return notificationDTOList;
    }

    public boolean addNotification(int order_id, String notificationType) throws SelfDesignException {
        //通知类型:结束招聘、录用、拒绝、接受、放弃
        Order order = orderMapper.selectById(order_id);
        if(order == null)
            throw new SelfDesignException("订单不存在");
        Notification notification = new Notification();
        Job job = jobMapper.selectById(order.getJobId());
        User user = userMapper.selectById(order.getJobhunterId());
//        QueryWrapper<RealnameAuthentication> realnameWrapper = new QueryWrapper<>();
//        realnameWrapper.eq("jobhunter_id", order.getJobhunterId()).eq("check_status","已通过");
//        RealnameAuthentication realname = realnameAuthenticationMapper.selectOne(realnameWrapper);
        if (notificationType.equals(NotificationType.CLOSE) || notificationType.equals(NotificationType.REFUSE)) {
            notification.setUserId(order.getJobhunterId());
            notification.setTitle("申请结果通知");
            notification.setContent("很遗憾的通知您，您对兼职“" + job.getWorkName() + "”提出的申请已被拒绝。");
            notification.setSendTime(new Date());
            notification.setUserType(UserType.JOBHUNTER);
        } else if (notificationType.equals(NotificationType.PASS)) {
            notification.setUserId(order.getJobhunterId());
            notification.setTitle("申请结果通知");
            notification.setContent("恭喜！您对兼职“" + job.getWorkName() + "”提出的申请已通过，请及时确认录用结果。");
            notification.setSendTime(new Date());
            notification.setUserType(UserType.JOBHUNTER);
        } else if (notificationType.equals(NotificationType.ACCEPT)) {
            notification.setUserId(job.getRecruiterId());
            notification.setTitle("Offer发放结果通知");
            notification.setContent("您好，求职者" + user.getNickname() + "已接受您发放的兼职“" + job.getWorkName() + "”的录用通知。");
            notification.setSendTime(new Date());
            notification.setUserType(UserType.RECRUITER);
        } else if (notificationType.equals(NotificationType.GIVEUP)) {
            notification.setUserId(job.getRecruiterId());
            notification.setTitle("Offer发放结果通知");
            notification.setContent("您好，求职者" + user.getNickname() + "放弃了您发放的兼职“" + job.getWorkName() + "”的录用通知。");
            notification.setSendTime(new Date());
            notification.setUserType(UserType.RECRUITER);
        }
        else
            throw new SelfDesignException("通知类型非法");
        int re = notificationMapper.insert(notification);
        return re > 0;
    }

    public boolean noticeRestJobhunter(List<Integer> order_list) throws SelfDesignException {
        boolean re = true;
        for (int order_id : order_list)
            re = re && addNotification(order_id, NotificationType.CLOSE);
        return re;
    }

    public boolean deleteNotification(int notificationId) throws SelfDesignException {
        if(notificationMapper.selectById(notificationId) == null)
            throw new SelfDesignException("不存在该通知信息");
        int re = notificationMapper.deleteById(notificationId);
        return re > 0;
    }


}




