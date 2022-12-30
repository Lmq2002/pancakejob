package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.jbgz.pancakejob.service.CompanyAuthenticationService;
import com.jbgz.pancakejob.mapper.CompanyAuthenticationMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【company_authentication】的数据库操作Service实现
* @createDate 2022-12-30 22:38:45
*/
@Service
public class CompanyAuthenticationServiceImpl extends ServiceImpl<CompanyAuthenticationMapper, CompanyAuthentication>
    implements CompanyAuthenticationService{

}




