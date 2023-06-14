package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class JobUpVO {
    private Integer recruiterId;

    private boolean ifRelease;

    private JobInfoVO jobInfo;
}
