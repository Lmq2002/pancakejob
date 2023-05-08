package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.NoticeDTO;
import com.jbgz.pancakejob.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.NoticeVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【notice】的数据库操作Service
* @createDate 2022-12-30 22:39:31
*/
public interface NoticeService extends IService<Notice> {

    List<NoticeDTO> getNoticeList(String status);

    boolean addNotice(NoticeVO noticeVO);

    boolean manageNotice(int noticeId,String status);

    boolean deleteNotice(int noticeId);
}
