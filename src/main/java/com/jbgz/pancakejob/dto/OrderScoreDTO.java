package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderScoreDTO {
    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("jobhunterScore")
    private int jobhunterScore;

    @JsonProperty("recruiterScore")
    private int recruiterScore;
}
