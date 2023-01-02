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
 * @TableName jobhunter_conlog
 */
@TableName(value ="jobhunter_conlog")
@Data
public class JobhunterConlog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer conlogId;

    /**
     * 
     */
    private String token;

    /**
     * 
     */
    private Date loginTime;

    /**
     * 
     */
    private Integer jobhunterId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}