package com.jbgz.pancakejob.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AuditVO {
    private Integer applyId;
    private String checkStatus;
    private String result;
    private Date checkTime;
}
