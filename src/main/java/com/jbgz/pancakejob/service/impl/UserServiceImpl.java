package com.jbgz.pancakejob.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.common.UserType;
import com.jbgz.pancakejob.dto.CreateAccountDTO;
import com.jbgz.pancakejob.entity.Administrator;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.mapper.AdministratorMapper;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.mapper.RecruiterMapper;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    @Autowired
    private JobhunterMapper jobhunterMapper;
    @Autowired
    private RecruiterMapper recruiterMapper;
    @Autowired
    private AdministratorMapper administratorMapper;
    @Override
    public User login(LoginVO vo) {
        return userMapper.findUserById(vo);
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
    public boolean regist(RegistVO vo) throws SelfDesignException {
        if(vo.getPassword()==null) throw new SelfDesignException("密码不能为空");
        if(vo.getEmail()==null) throw new SelfDesignException("邮箱不能为空");
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email",vo.getEmail());
        int tmp = userMapper.selectCount(qw);
        if(tmp!=0)
        {
            throw new SelfDesignException("该邮箱已被注册");
        }
        User user = new User();
        user.setEmail(vo.getEmail());
        user.setUserType(vo.getUserType());
        user.setPassword(vo.getPassword());
        user.setRegistrationTime(new Date());
//        user.setContactMethod(100L);
        tmp = userMapper.insert(user);
        if(tmp!=0 && regist2(vo,user.getUserId())){
            return true;
        }
        if(tmp!=0){
            userMapper.deleteById(user.getUserId());
        }
        return false;
    }

    @Override
    public boolean regist2(RegistVO vo, Integer id) throws SelfDesignException {
        boolean tmp = false;
        if (id==null) throw new SelfDesignException("ID不能为空");
        if(id<10000)
            throw new SelfDesignException("ID不合法");

        if(vo.getUserType().equals(UserType.ADMIN)){
            Administrator admin = new Administrator();
            admin.setAdminId(id);
            admin.setPassword(vo.getPassword());
            System.out.println("准备插入:"+admin.getAdminId()+"  "+admin.getPassword());
            if(1 == administratorMapper.registAdmin(admin))return true;
        }
        else if(vo.getUserType().equals(UserType.RECRUITER)){
            Recruiter recruiter = new Recruiter();
            recruiter.setRecruiterId(id);
            if(1 == recruiterMapper.registRecruiter(recruiter))return true;
        }
        else if(vo.getUserType().equals(UserType.JOBHUNTER)){
            Jobhunter jobhunter = new Jobhunter();
            jobhunter.setJobhunterId(id);
            if(1 == jobhunterMapper.registJobhunter(jobhunter))return true;
        }
        else throw new SelfDesignException("用户类型不合法");
        return false;
    }

    @Override
    public boolean alterPassword(FindPasswordVO vo) {
        return userMapper.alterPassword(vo);
    }

    @Override
    public boolean setContactMethod(ContactMethodVO vo) {
        if(userMapper.setContactMethod(vo))
            return true;
        return false;
    }
}




