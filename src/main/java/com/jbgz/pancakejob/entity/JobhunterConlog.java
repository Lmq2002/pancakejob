package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class JobhunterConlog {
    private Integer conlog_id;
    private String ip;
    private Date login_time;
    private Integer jobhunter_id;
}
