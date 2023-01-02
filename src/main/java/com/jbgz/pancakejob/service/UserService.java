package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.CreateAccountDTO;
import com.jbgz.pancakejob.dto.LoginDTO;
import com.jbgz.pancakejob.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.vo.EmailAccountVO;
import com.jbgz.pancakejob.vo.FindPasswordVO;
import com.jbgz.pancakejob.vo.RegistVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【user】的数据库操作Service
* @createDate 2022-12-30 22:46:41
*/
public interface UserService extends IService<User> {
    

    List<LoginDTO> login();
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

    /**
     * 根据user_id修改密码
     * 返回boolean
     * */
    boolean alterPassword(FindPasswordVO vo);
}
