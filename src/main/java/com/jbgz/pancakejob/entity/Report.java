package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private Integer jobhunter_id;
    private Integer job_id;
    private Date report_time;
    private String report_reason;
    private String report_state;
    private String report_redult;
}
