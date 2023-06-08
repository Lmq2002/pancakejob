package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class ReportDealVO {
    private Integer jobhunterId;

    private Integer jobId;

    private String reportResult;

    private boolean reportState;
}
