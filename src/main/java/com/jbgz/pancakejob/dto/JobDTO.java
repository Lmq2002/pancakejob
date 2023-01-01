package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobDTO {
    @JsonProperty("jobId")
    private int jobId;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("releaseTime")
    private String releaseTime;

    @JsonProperty("jobState")
    private String jobState;

    @JsonProperty("jobType")
    private String jobType;

    @JsonProperty("workName")
    private String workName;

    @JsonProperty("workTime")
    private String workTime;

    @JsonProperty("workPlace")
    private String workPlace;

    @JsonProperty("workDetails")
    private String workDetails;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;
}