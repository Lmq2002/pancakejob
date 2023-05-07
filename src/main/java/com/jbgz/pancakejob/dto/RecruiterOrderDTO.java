package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecruiterOrderDTO {
    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("jobId")
    private int jobId;

    @JsonProperty("recruiterId")
    private int recruiterId;

    @JsonProperty("recruiterScore")
    private int recruiterScore;
}
