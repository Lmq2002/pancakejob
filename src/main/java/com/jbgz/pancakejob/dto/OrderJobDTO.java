package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderJobDTO {
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
}
