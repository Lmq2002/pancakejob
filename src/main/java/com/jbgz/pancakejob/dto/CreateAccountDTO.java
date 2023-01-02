package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAccountDTO {
    @JsonProperty("captcha")
    private String captcha;
}
