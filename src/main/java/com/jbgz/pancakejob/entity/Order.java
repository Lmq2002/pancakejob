package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer order_id;
    private Integer jobhunter_id;
    private Integer job_id;
    private Date apply_time;
    private String apply_description;
    private String order_state;
    private Date pass_time;
    private Float jobhunter_score;
    private Float recuriter_score;

}
