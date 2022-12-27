package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RealnameAuthentication {
    private Integer apply_id;
    private String idcard;
    private String identification;
    private String real_name;
    private Integer jobhunter_id;
    private Date apply_time;
    private String check_status;
    private Date checkout_time;
    private String RESULT;
}
