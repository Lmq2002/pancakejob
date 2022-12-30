package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-12-30 22:46:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




