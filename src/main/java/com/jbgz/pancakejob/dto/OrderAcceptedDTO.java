package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderAcceptedDTO {
    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("orderState")
    private String orderState;

    @JsonProperty("jobhunter")
    private JobhunterDTO jobhunter;

    @JsonProperty("orderScore")
    private OrderScoreDTO orderScore;

}
