package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Session {
    private Integer session_id;
    private Date create_time;
    private Date delete_time;
    private Integer recuriter_id;
    private Integer jobhunter_id;

}
