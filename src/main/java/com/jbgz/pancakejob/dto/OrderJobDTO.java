package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderJobDTO {

    @JsonProperty("jobId")
    private Integer jobId;

    @JsonProperty("workName")
    private String workName;

    @JsonProperty("workPlace")
    private String workPlace;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("workTime")
    private int workTime;

    @JsonProperty("score")
    private BigDecimal score;
}
