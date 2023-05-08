package com.jbgz.pancakejob.vo;

import lombok.Data;

@Data
public class NoticeVO {

    private int adminId;

    private String content;

    private String status;

    private String title;

    private String imgURL;
}
