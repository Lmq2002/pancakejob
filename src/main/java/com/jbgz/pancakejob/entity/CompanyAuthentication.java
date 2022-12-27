package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyAuthentication {
    private Integer apply_id;
    private String company_name;
    private String company_id;
    private String company_type;
    private String certification;
    private Integer recuriter_id;
    private Date apply_time;
    private String check_status;
    private Date check_time;
    private String Result;
}
