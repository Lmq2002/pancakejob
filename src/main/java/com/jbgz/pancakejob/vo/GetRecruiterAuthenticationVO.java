package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class GetRecruiterAuthenticationVO {
    private Integer recruiterId;
    private String companyName;
    private String companyId;
    private String companyType;
    private String certification;
}
