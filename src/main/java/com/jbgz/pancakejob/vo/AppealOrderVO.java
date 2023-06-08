package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class AppealOrderVO {
    private Integer orderId;

    private String appealContent;

    private String appealType;
}
