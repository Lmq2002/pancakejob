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
 * @TableName report
 */
@TableName(value ="report")
@Data
public class Report implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer jobhunterId;

    /**
     * 
     */
    @TableId
    private Integer jobId;

    /**
     * 
     */
    @TableId
    private Date reportTime;

    /**
     * 
     */
    private String reportReason;

    /**
     * 
     */
    private String reportState;

    /**
     * 
     */
    private String reportResult;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}