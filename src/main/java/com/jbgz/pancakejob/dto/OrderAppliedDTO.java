package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderAppliedDTO {
    @JsonProperty("jobhunter")
    private JobhunterDTO jobhunter;

    @JsonProperty("applyDescription")
    private String applyDescription;

    @JsonProperty("orderState")
    private String orderState;

    @JsonProperty("orderId")
    private int orderId;

}
