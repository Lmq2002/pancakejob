package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppealDTO {

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("appealType")
    private String appealType;

    @JsonProperty("appealContent")
    private String appealContent;

    @JsonProperty("appealTime")
    private String appealTime;

    @JsonProperty("appealResult")
    private String appealResult;

    @JsonProperty("status")
    private String status;
}
