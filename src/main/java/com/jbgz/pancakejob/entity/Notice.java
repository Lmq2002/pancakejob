package com.jbgz.pancakejob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName notice
 */
@TableName(value = "notice")
@Data
public class Notice implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer noticeId;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private String status;

    /**
     *
     */
    private Date announceTime;

    /**
     *
     */
    private Integer adminId;

    /**
     *
     */
    private String imgUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}