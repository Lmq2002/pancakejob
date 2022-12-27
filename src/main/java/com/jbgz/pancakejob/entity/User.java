package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer user_id;
    private String user_type;
    private String password;
    private String nickname;
    private String headportrait;
    private String email;
    private Date regidtration_time;
    private Integer contact_method;
    private String introduction;
    private Float score;
}
