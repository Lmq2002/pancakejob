package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class AppealDealVO {

    private int orderId;

    private String appealType;

    private String appealResult;

    private boolean status;
}
