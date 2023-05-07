package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class ReportDealVO {
    private int jobhunterId;

    private int jobId;

    private String reportResult;

    private boolean reportState;
}
