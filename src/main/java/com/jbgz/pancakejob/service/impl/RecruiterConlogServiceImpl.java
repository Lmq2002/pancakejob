package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.RecruiterConlog;
import com.jbgz.pancakejob.service.RecruiterConlogService;
import com.jbgz.pancakejob.mapper.RecruiterConlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author CSY0214
* @description 针对表【recruiter_conlog】的数据库操作Service实现
* @createDate 2022-12-30 22:40:02
*/
@Service
public class RecruiterConlogServiceImpl extends ServiceImpl<RecruiterConlogMapper, RecruiterConlog>
    implements RecruiterConlogService {

    @Autowired
    private RecruiterConlogMapper mapper;
    @Override
    public boolean create(Integer recruiter_id, String token) {
        RecruiterConlog conlog = new RecruiterConlog();
        conlog.setRecruiterId(recruiter_id);
        conlog.setToken(token);
        conlog.setLoginTime(new Date());
        int tmp = mapper.insert(conlog);
        if(tmp<1)
            return false;
        return true;
    }
}




