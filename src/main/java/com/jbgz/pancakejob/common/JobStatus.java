package com.jbgz.pancakejob.common;

public interface JobStatus {
    String WAIT = "未审核";
    String COMPLETE = "已完成";
    String OVER="已结束";
    String ACCESS = "已通过";
    String DRAFT = "未发布";
}
