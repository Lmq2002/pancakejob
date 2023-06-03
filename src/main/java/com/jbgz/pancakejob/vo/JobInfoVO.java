package com.jbgz.pancakejob.vo;

import lombok.Data;
/**
 * 接收前端传参
 * */
import java.math.BigDecimal;

@Data
public class JobInfoVO {
    private String jobName;

    private Integer jobType;

    private String workDetails;

    private Integer workTime;

    private String startTime;

    private String endTime;

    private String workPlace;

    private BigDecimal salary;

    private int employeeNum;

}
