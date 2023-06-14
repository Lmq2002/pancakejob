package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.utils.SelfDesignException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private NotificationService notificationService;

    @GetMapping("/getNotificationList")
    public ResultData getNotificationList(Integer userId) {
        try {
            if(userId == null)
                throw new SelfDesignException("用户ID为空");
            ResultData result = new ResultData();
            result.data.put("notification_list", notificationService.getNotificationList(userId));
            result.code = Constants.CODE_200;
            result.message = "获取通知列表成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }

    @DeleteMapping("/deleteNotification")
    public ResultData deleteNotification(Integer notificationId) {
        try {
            if(notificationId == null)
                throw new SelfDesignException("通知ID为空");
            ResultData result = new ResultData();
            boolean re = notificationService.deleteNotification(notificationId);
            result.code = Constants.CODE_200;
            if (re)
                result.message = "删除通知成功";
            else
                result.message = "删除通知失败";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }
}