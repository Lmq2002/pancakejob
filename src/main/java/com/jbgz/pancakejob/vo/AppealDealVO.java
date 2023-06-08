package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class AppealDealVO {

    private Integer orderId;

    private String appealType;

    private String appealResult;

    private boolean status;
}
