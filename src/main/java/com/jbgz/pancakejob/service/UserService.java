package com.jbgz.pancakejob.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 封装mapper接口函数，以供controller调用。
 * */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    private UserMapper userMapper;
    public int find(User user)
    {
        return userMapper.find(user);
    }
}
