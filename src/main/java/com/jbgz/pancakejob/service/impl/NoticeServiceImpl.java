package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.NoticeDTO;
import com.jbgz.pancakejob.entity.Notice;
import com.jbgz.pancakejob.service.NoticeService;
import com.jbgz.pancakejob.mapper.NoticeMapper;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.vo.NoticeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CSY0214
 * @description 针对表【notice】的数据库操作Service实现
 * @createDate 2022-12-30 22:39:31
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
        implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    NoticeDTO getNoticeDTO(Notice notice) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeId(notice.getNoticeId());
        noticeDTO.setContent(notice.getContent());
        noticeDTO.setStatus(notice.getStatus());
        noticeDTO.setTitle(notice.getTitle());
        noticeDTO.setAdminId(notice.getAdminId());
        noticeDTO.setImgURL(notice.getImgUrl());
        noticeDTO.setAnnounceTime(DateTimeTrans.datetimeToString(notice.getAnnounceTime()));
        return noticeDTO;
    }

    List<NoticeDTO> getNoticeListDTO(List<Notice> noticeList) {
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for (Notice notice : noticeList) {
            NoticeDTO noticeDTO = getNoticeDTO(notice);
            noticeDTOList.add(noticeDTO);
        }
        return noticeDTOList;
    }

    //获取全部公告列表or某状态的公告列表
    public List<NoticeDTO> getNoticeList(String status) {
        QueryWrapper<Notice> noticeWrapper = new QueryWrapper<>();
        if (status != null)
            noticeWrapper.eq("status", status);
        noticeWrapper.orderByDesc("announce_time");
        List<Notice> noticeList = noticeMapper.selectList(noticeWrapper);
        List<NoticeDTO> noticeDTOList = getNoticeListDTO(noticeList);
        return noticeDTOList;
    }

    //添加公告-未发布or直接发布-已发布
    public boolean addNotice(NoticeVO noticeVO) {
        Notice notice = new Notice();
        notice.setAdminId(noticeVO.getAdminId());
        notice.setTitle(noticeVO.getTitle());
        notice.setContent(noticeVO.getContent());
        notice.setAnnounceTime(new Date());
        notice.setImgUrl(noticeVO.getImgURL());
        notice.setStatus(noticeVO.getStatus());
        System.out.println("公告内容："+ notice);
        int re = noticeMapper.insert(notice);
        return re > 0;
    }

    //发布、撤销公告
    public boolean manageNotice(int noticeId, String status) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setStatus(status);
        int re = noticeMapper.updateById(notice);
        return re > 0;
    }

    //删除公告
    public boolean deleteNotice(int noticeId) {
        int re = noticeMapper.deleteById(noticeId);
        return re > 0;
    }

}




