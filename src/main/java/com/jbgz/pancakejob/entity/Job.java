package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Job {
    private Integer job_id;
    private Integer recuriter_id;
    private Date release_time;
    private String job_state;
    private  Integer job_type;
    private String work_name;
    private  Date work_time;
    private String work_place;
    private  String work_details;
    private Float slalry;
    private Date start_time;
    private Date end_time;
}
