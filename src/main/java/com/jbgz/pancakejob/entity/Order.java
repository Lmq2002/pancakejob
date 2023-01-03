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
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 
     */
    private Integer jobhunterId;

    /**
     * 
     */
    private Integer jobId;

    /**
     * 
     */
    private Date applyTime;

    /**
     * 
     */
    private String applyDescription;

    /**
     * 
     */
    private String orderState;

    /**
     * 
     */
    private Date passTime;

    /**
     * 
     */
    private Integer jobhunterScore;

    /**
     * 
     */
    private Integer recruiterScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}