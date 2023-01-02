package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.AdminConlog;
import com.jbgz.pancakejob.service.AdminConlogService;
import com.jbgz.pancakejob.mapper.AdminConlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author CSY0214
* @description 针对表【admin_conlog】的数据库操作Service实现
* @createDate 2022-12-30 22:09:50
*/
@Service
public class AdminConlogServiceImpl extends ServiceImpl<AdminConlogMapper, AdminConlog>
    implements AdminConlogService{

    @Resource
    private AdminConlogMapper mapper;
    @Override
    public boolean create(Integer admin_id,String token) {
        AdminConlog conlog = new AdminConlog();
        conlog.setAdminId(admin_id);
        conlog.setToken(token);
        conlog.setLoginTime(new Date());

        int tmp = mapper.insert(conlog);
        if(tmp<1)
            return false;
        return true;
    }
}




