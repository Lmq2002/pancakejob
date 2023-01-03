package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.CreateAccountDTO;
import com.jbgz.pancakejob.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.*;

/**
* @author CSY0214
* @description 针对表【user】的数据库操作Service
* @createDate 2022-12-30 22:46:41
*/
public interface UserService extends IService<User> {
    

    User login(LoginVO vo);
    /**
     * 调用雪花算法，生成邮箱验证码，发送邮箱
     * 返回：AccountDTO
     * */
    CreateAccountDTO createAccount(EmailAccountVO vo);

    /**
     * 查找数据库，是否存在email
     * 返回：boolean
     * */
    boolean findEmail(EmailAccountVO vo);

    /**
     * 插入新用户
     * 返回 boolean
     * */
    boolean regist(RegistVO vo);

    boolean regist2(RegistVO vo,Integer id);

    /**
     * 根据user_id修改密码
     * 返回boolean
     * */
    boolean alterPassword(FindPasswordVO vo);

    /**
     * 根据userId修改或者添加contactMethod
     * 返回boolean
     * */
    boolean setContactMethod(ContactMethodVO vo);

}
