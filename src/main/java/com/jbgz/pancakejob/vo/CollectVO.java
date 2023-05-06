package com.jbgz.pancakejob.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CollectVO {
    private Integer jobhunterId;
    private Integer jobId;
    private Integer collectPosition;
    private Date collectTime;
}
