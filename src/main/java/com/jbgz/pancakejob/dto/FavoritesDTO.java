package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FavoritesDTO {
    private Integer jobId;
    private String workName;
    private String workPlace;
    private String startTime;
    private String endTime;
    private int workTime;
    private String salary;
    private String jobType;
    private int workerNum;
}
