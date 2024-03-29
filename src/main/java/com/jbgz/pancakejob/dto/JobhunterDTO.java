package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobhunterDTO {

    @JsonProperty("jobhunterId")
    private int jobhunterId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("headportrait")
    private String headportrait;

    @JsonProperty("school")
    private String school;

    @JsonProperty("email")
    private String email;

    @JsonProperty("score")
    private BigDecimal score;

}
