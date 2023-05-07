package com.jbgz.pancakejob.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class JobData {
    private String jobId;
    private String jobName;
    private String jobType;
    private String workDetails;
    private Integer workTime;
    private String startTime;
    private String endTime;
    private String workPlace;
    private BigDecimal salary;
    private Integer employeeNum;

}
