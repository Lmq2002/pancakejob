package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class JobUpVO {
    private int recuriterId;

    private boolean ifRelease;

    private JobInfoVO jobInfo;
}
