package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NoticeDTO {
    @JsonProperty("noticeId")
    private int noticeId;

    @JsonProperty("adminId")
    private int adminId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("status")
    private String status;

    @JsonProperty("announceTime")
    private String announceTime;

    @JsonProperty("imgURL")
    private String imgURL;
}
