package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonalInfoDTO {
    private String nickname;
    private String headportrait;
    private String contactMethod;
    private String email;
    private String introduction;
    private String birthday;
    private String school;
    private BigDecimal score;

}
