package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderAcceptedDTO {
    @JsonProperty("jobhunter")
    private JobhunterDTO jobhunter;

    @JsonProperty("order")
    private OrderScoreDTO order;

}
