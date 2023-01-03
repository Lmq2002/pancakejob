package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.ContactMethodVO;
import com.jbgz.pancakejob.vo.EmailAccountVO;
import com.jbgz.pancakejob.vo.FindPasswordVO;
import com.jbgz.pancakejob.vo.LoginVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-12-30 22:46:41
* @Entity com.jbgz.pancakejob.entity.User
*/

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查找用户
     * */
    @Select("SELECT * FROM user WHERE user_id = #{account} AND password = #{password}")
    User findUserById(LoginVO login);

    @Select("SELECT email, password FROM user WHERE email = #{email}")
    List<User> confirmEmail(EmailAccountVO vo);

    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userId}")
    boolean alterPassword(FindPasswordVO vo);

    @Update("UPDATE user SET contact_method = #{contactMethod} WHERE user_id = #{userId}")
    boolean setContactMethod(ContactMethodVO vo);



}




