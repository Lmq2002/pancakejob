package com.jbgz.pancakejob.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FavoritesDir {
    private Integer dir_id;
    private Integer jobhunter_id;
    private String dir_name;
    private Date create_time;
}
