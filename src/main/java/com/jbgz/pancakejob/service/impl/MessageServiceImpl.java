package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Message;
import com.jbgz.pancakejob.service.MessageService;
import com.jbgz.pancakejob.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【message】的数据库操作Service实现
* @createDate 2022-12-30 22:39:24
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




