package com.jbgz.pancakejob.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyAuthenDTO {
    private Integer applyId;
    private String companyName;
    private String companyId;
    private String companyType;
    private String certification;
    private Integer recruiterId;
    private Date applyTime;
    private String checkStatus;
    private Date checkTime;
    private String result;
}
