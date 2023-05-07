package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonAuthenDTO {
    private Integer applyId;
    private String idCard;
    private String identification;
    private String realName;
    private Integer jobhunterId;
    private Date applyTime;
    private String checkStatus;
    private Date checkTime;
    private String result;
}
