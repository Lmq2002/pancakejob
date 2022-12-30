package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Notice;
import com.jbgz.pancakejob.service.NoticeService;
import com.jbgz.pancakejob.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2022-12-30 22:39:31
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




