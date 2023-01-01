package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class JobInfoVO {
    private String jobName;

    private String jobType;

    private String workDetails;

    private String startTime;

    private String endTime;

    private String workPlace;

    private double salary;

    private int employeeNum;

}
