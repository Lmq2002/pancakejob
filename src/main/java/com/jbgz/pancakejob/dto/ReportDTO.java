package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReportDTO {
    @JsonProperty("jobhunterId")
    private int jobhunterId;

    @JsonProperty("jobId")
    private int jobId;

    @JsonProperty("reportTime")
    private String reportTime;

    @JsonProperty("reportReason")
    private String reportReason;

    @JsonProperty("reportState")
    private String reportState;

    @JsonProperty("reportResult")
    private String reportResult;
}
