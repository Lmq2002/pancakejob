package com.jbgz.pancakejob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName job
 */
@TableName(value ="job")
@Data
public class Job implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer jobId;

    /**
     * 
     */
    private Integer recuriterId;

    /**
     * 
     */
    private Date releaseTime;

    /**
     * 
     */
    private String jobState;

    /**
     * 
     */
    private Integer jobType;

    /**
     * 
     */
    private String workName;

    /**
     * 
     */
    private Date workTime;

    /**
     * 
     */
    private String workPlace;

    /**
     * 
     */
    private String workDetails;

    /**
     * 
     */
    private BigDecimal salary;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}