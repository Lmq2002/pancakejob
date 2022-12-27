package com.jbgz.pancakejob.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Data
public class AdminConlog {
    private Integer conlog_id;
    private String ip;
    private Date login_time;
    private Integer admin_id;
}
