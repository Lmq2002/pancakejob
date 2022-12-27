package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notification {
    private Integer notification_id;
    private String title;
    private String content;
    private Date send_time;
    private Integer user_id;
    private String user_type;
}
