package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FavoritesDTO {
    private Integer jobId;
    private String workName;
    private String workPlace;
    private Date startTime;
    private Date endTime;
    private Integer workerNum;
    private Integer workTime;
    private String salary;
    private String jobType;
}
