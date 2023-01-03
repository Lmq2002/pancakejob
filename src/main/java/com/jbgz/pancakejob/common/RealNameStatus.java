package com.jbgz.pancakejob.common;

import lombok.Data;


public interface RealNameStatus {
    String WAITING = "未审核";
     String SUCCESS = "已通过";
    String FAIL = "未通过";
}
