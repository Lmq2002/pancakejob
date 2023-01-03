package com.jbgz.pancakejob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName company_authentication
 */
@TableName(value ="company_authentication")
@Data
public class CompanyAuthentication implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer applyId;

    /**
     * 
     */
    private String companyName;

    /**
     * 
     */
    private String companyId;

    /**
     * 
     */
    private String companyType;

    /**
     * 
     */
    private String certification;

    /**
     * 
     */
    private Integer recruiterId;

    /**
     * 
     */
    private Date applyTime;

    /**
     * 
     */
    private String checkStatus;

    /**
     * 
     */
    private Date checkTime;

    /**
     * 
     */
    private String result;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}