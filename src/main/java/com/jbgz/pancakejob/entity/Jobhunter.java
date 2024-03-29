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
 * @TableName jobhunter
 */
@TableName(value ="jobhunter")
@Data
public class Jobhunter implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer jobhunterId;

    /**
     * 
     */
    private Date birthday;

    /**
     * 
     */
    private String school;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}