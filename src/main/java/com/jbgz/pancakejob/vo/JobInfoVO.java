package com.jbgz.pancakejob.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobInfoVO {
    private String jobName;

    private int jobType;

    private String workDetails;

    private int workTime;

    private String startTime;

    private String endTime;

    private String workPlace;

    private BigDecimal salary;

    private int employeeNum;

}
