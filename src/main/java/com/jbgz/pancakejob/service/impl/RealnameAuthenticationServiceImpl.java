package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.common.RealNameStatus;
import com.jbgz.pancakejob.dto.PersonAuthenDTO;
import com.jbgz.pancakejob.entity.RealnameAuthentication;
import com.jbgz.pancakejob.service.RealnameAuthenticationService;
import com.jbgz.pancakejob.mapper.RealnameAuthenticationMapper;
import com.jbgz.pancakejob.vo.AuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【realname_authentication】的数据库操作Service实现
* @createDate 2022-12-30 22:39:44
*/
@Service
public class RealnameAuthenticationServiceImpl extends ServiceImpl<RealnameAuthenticationMapper, RealnameAuthentication>
    implements RealnameAuthenticationService{

    @Autowired
    private RealnameAuthenticationMapper mapper;

    public boolean auditAuthentication(AuditVO vo){
        vo.setCheckTime(new Date());
        return mapper.auditAuthentication(vo);
    }
    public List<PersonAuthenDTO> getAuthenList(Integer jobhunterId){
        if(jobhunterId!=null) return mapper.getOne(jobhunterId);
        else return mapper.getList();
    }

    public List<PersonAuthenDTO> getAuthen(Integer jobhunterId){
        return mapper.getOne(jobhunterId);
    }

}




