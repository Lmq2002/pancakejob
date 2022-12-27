package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer message_id;
    private Integer sender_id;
    private Integer receiver_id;
    private String content;
    private Date send_time;
    private String status;
    private Integer session_id;
}
