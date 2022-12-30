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
 * @TableName realname_authentication
 */
@TableName(value ="realname_authentication")
@Data
public class RealnameAuthentication implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object applyId;

    /**
     * 
     */
    private String idcard;

    /**
     * 
     */
    private String identification;

    /**
     * 
     */
    private String realName;

    /**
     * 
     */
    private Integer jobhunterId;

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