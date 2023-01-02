package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.JobhunterConlog;
import com.jbgz.pancakejob.service.JobhunterConlogService;
import com.jbgz.pancakejob.mapper.JobhunterConlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author CSY0214
* @description 针对表【jobhunter_conlog】的数据库操作Service实现
* @createDate 2022-12-30 22:39:19
*/
@Service
public class JobhunterConlogServiceImpl extends ServiceImpl<JobhunterConlogMapper, JobhunterConlog>
    implements JobhunterConlogService{

    @Autowired
    private JobhunterConlogMapper mapper;
    @Override
    public boolean create(Integer jobhunter_id, String token) {
        JobhunterConlog conlog = new JobhunterConlog();
        conlog.setJobhunterId(jobhunter_id);
        conlog.setToken(token);
        conlog.setLoginTime(new Date());
        int tmp = mapper.insert(conlog);
        if (tmp<1)return false;
        return true;
    }
}




