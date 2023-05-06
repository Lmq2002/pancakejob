package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.entity.RealnameAuthentication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.AuditVO;

/**
* @author CSY0214
* @description 针对表【realname_authentication】的数据库操作Service
* @createDate 2022-12-30 22:39:44
*/
public interface RealnameAuthenticationService extends IService<RealnameAuthentication> {

    boolean auditAuthentication(AuditVO vo);

}
