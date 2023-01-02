package com.jbgz.pancakejob.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.CreateAccountDTO;
import com.jbgz.pancakejob.dto.LoginDTO;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.vo.EmailAccountVO;
import com.jbgz.pancakejob.vo.FindPasswordVO;
import com.jbgz.pancakejob.vo.RegistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-12-30 22:46:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<LoginDTO> login() {
        return null;
    }



    @Override
    public CreateAccountDTO createAccount(EmailAccountVO vo) {
        //雪花算法生成确认码
        //String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();
        String confirmCode = RandomUtil.randomString(6);
        MailUtil.send(vo.getEmail(),"test_emial",confirmCode,false);
        CreateAccountDTO dto = new CreateAccountDTO();
        dto.setCaptcha(confirmCode);
//        String salt = RandomUtil.randomString(6);
//        //加密密码
//        String md5Pwd = SecureUtil.md5(user.getPassword()+salt);
//        //激活失效时间，1天
//        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
//
//        //新增账号
//        int result = userMapper.insert(user);
//        CreateAccountDTO accountDto = new CreateAccountDTO();
//        if(result>0) {
//            //发送邮件
//
//        }
//        else
//            return null;
        return dto;
    }

    @Override
    public boolean findEmail(EmailAccountVO vo) {
        System.out.println("email:"+vo.getEmail());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email",vo.getEmail());
        int tmp = userMapper.selectCount(qw);
        System.out.println("count:"+tmp);
        if(tmp>0)
            return true;
        return false;
    }

    @Override
    public boolean regist(RegistVO vo) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email",vo.getEmail());
        int tmp = userMapper.selectCount(qw);
        if(tmp!=0)
            return false;
        User user = new User();
        user.setEmail(vo.getEmail());
        user.setUserType(vo.getUserType());
        user.setPassword(vo.getPassword());
        user.setRegistrationTime(new Date());
        user.setContactMethod(100L);
        tmp = userMapper.insert(user);
        if(tmp!=0){
            return true;
        }
        return false;
    }

    @Override
    public boolean alterPassword(FindPasswordVO vo) {
        return userMapper.alterPassword(vo);
    }
}




