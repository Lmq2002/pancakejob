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
 * @TableName favorites_dir
 */
@TableName(value ="favorites_dir")
@Data
public class FavoritesDir implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer favoritesDirId;

    /**
     * 
     */
    private Integer jobhunterId;

    /**
     * 
     */
    private String favoritesDirName;

    /**
     * 
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}