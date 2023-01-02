package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.RecuriterConlog;
import com.jbgz.pancakejob.service.RecuriterConlogService;
import com.jbgz.pancakejob.mapper.RecuriterConlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author CSY0214
* @description 针对表【recuriter_conlog】的数据库操作Service实现
* @createDate 2022-12-30 22:40:02
*/
@Service
public class RecuriterConlogServiceImpl extends ServiceImpl<RecuriterConlogMapper, RecuriterConlog>
    implements RecuriterConlogService{

    @Autowired
    private RecuriterConlogMapper mapper;
    @Override
    public boolean create(Integer recuriter_id, String token) {
        RecuriterConlog conlog = new RecuriterConlog();
        conlog.setRecuriterId(recuriter_id);
        conlog.setToken(token);
        conlog.setLoginTime(new Date());
        int tmp = mapper.insert(conlog);
        if(tmp<1)
            return false;
        return true;
    }
}




