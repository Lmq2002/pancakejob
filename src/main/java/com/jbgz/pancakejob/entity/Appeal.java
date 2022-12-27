package com.jbgz.pancakejob.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Data
public class Appeal {
    private Integer order_id;
    private String appeal_type;
    private String appeal_content;
    private Date appeal_time;
    private String appeal_result;

}
