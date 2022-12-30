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
 * @TableName collect_job
 */
@TableName(value ="collect_job")
@Data
public class CollectJob implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer jobhunterId;

    /**
     * 
     */
    @TableId
    private Integer jobId;

    /**
     * 
     */
    private Date collectTime;

    /**
     * 
     */
    private Integer collectPosition;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}