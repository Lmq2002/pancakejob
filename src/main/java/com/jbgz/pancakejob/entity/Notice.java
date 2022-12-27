package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Integer notice_id;
    private String title;
    private String content;
    private String status;
    private Date announce_time;
    private Integer admin_id;
}
