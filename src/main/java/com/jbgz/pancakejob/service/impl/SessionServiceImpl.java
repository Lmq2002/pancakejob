package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Session;
import com.jbgz.pancakejob.service.SessionService;
import com.jbgz.pancakejob.mapper.SessionMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【session】的数据库操作Service实现
* @createDate 2022-12-30 22:40:12
*/
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session>
    implements SessionService{

}




