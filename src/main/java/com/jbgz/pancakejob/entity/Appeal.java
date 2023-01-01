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
 * @TableName appeal
 */
@TableName(value ="appeal")
@Data
public class Appeal implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer orderId;

    /**
     * 
     */
    @TableId
    private String appealType;

    /**
     * 
     */
    private String appealContent;

    /**
     * 
     */
    private Date appealTime;

    /**
     * 
     */
    private String appealResult;

    /**
     *
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}