package com.jbgz.pancakejob.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName recuriter
 */
@TableName(value ="recuriter")
@Data
public class Recruiter implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer recruiterId;

    /**
     * 
     */
    private String companyName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}