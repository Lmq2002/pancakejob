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
 * @TableName session
 */
@TableName(value ="session")
@Data
public class Session implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer sessionId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date jobhunterDeleteTime;

    /**
     * 
     */
    private Integer recuriterId;

    /**
     * 
     */
    private Integer jobhunterId;

    /**
     * 
     */
    private Date recuriterDeleteTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}