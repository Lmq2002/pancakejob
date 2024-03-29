package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.entity.RecruiterConlog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author CSY0214
* @description 针对表【recruiter_conlog】的数据库操作Service
* @createDate 2022-12-30 22:40:02
*/
public interface RecruiterConlogService extends IService<RecruiterConlog> {
    boolean create(Integer recruiter_id,String token);
}
