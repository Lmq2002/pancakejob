package com.jbgz.pancakejob.dto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecruiterPersonInfoDTO {
    private Integer recruiterId;
    private String companyName;
    private String nickname;
    private String headportrait;
    private String contactMethod;
    private String email;
    private String introduction;
    private BigDecimal score;
}
