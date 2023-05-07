package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.CompanyAuthenDTO;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.AuditVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【company_authentication】的数据库操作Service
* @createDate 2022-12-30 22:38:45
*/
public interface CompanyAuthenticationService extends IService<CompanyAuthentication> {

    boolean auditAuthentication(AuditVO vo);

    List<CompanyAuthenDTO> getAuthenList(Integer recruiterId);

    List<CompanyAuthenDTO> getAuthen(Integer recruiterId);
}
