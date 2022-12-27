package com.jbgz.pancakejob.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * sql语句，后端和数据库接口类。
 * 可能不需要对应entity里的类，当然如果全用plus框架应该就要对应了。
 * */


public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROME user")
    List<User> findAll();

    @Select("SELECT *FROM user WHERE user_id=#{user_id}")
    int find(User user);
}
