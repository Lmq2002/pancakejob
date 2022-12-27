package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CollectJob {
    private Integer jobhunter_id;
    private Integer job_id;
    private Date collect_time;
    private Integer collect_position;
}
