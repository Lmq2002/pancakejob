package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.CompanyAuthenDTO;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.jbgz.pancakejob.mapper.RealnameAuthenticationMapper;
import com.jbgz.pancakejob.service.CompanyAuthenticationService;
import com.jbgz.pancakejob.mapper.CompanyAuthenticationMapper;
import com.jbgz.pancakejob.vo.AuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【company_authentication】的数据库操作Service实现
* @createDate 2022-12-30 22:38:45
*/
@Service
public class CompanyAuthenticationServiceImpl extends ServiceImpl<CompanyAuthenticationMapper, CompanyAuthentication>
    implements CompanyAuthenticationService{

    @Autowired
    private CompanyAuthenticationMapper mapper;

    public boolean auditAuthentication(AuditVO vo){
        vo.setCheckTime(new Date());
        return mapper.auditAuthentication(vo);
    }

    public List<CompanyAuthenDTO> getAuthenList(Integer recruiterId){
        if(recruiterId!=null) return mapper.getOne(recruiterId);
        else return mapper.getList();
    }

    public List<CompanyAuthenDTO> getAuthen(Integer recruiterId){
        return mapper.getOne(recruiterId);
    }

}




