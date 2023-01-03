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
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer messageId;

    /**
     * 
     */
    private Integer senderId;

    /**
     * 
     */
    private Integer receiverId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date sendTime;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Integer sessionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}