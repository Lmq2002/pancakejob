package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

}
