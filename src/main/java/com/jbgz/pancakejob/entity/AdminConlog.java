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
 * @TableName admin_conlog
 */
@TableName(value ="admin_conlog")
@Data
public class AdminConlog implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer conlogId;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    private Date loginTime;

    /**
     * 
     */
    private Integer adminId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}