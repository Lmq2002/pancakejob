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
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 
     */
    private String userType;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String headportrait;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Date registrationTime;

    /**
     * 
     */
    private Long contactMethod;

    /**
     * 
     */
    private String introduction;

    /**
     * 
     */
    private BigDecimal score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}