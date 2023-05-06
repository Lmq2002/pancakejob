package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.AuditVO;
import org.apache.ibatis.annotations.Update;

/**
* @author CSY0214
* @description 针对表【company_authentication】的数据库操作Mapper
* @createDate 2022-12-30 22:38:45
* @Entity com.jbgz.pancakejob.entity.CompanyAuthentication
*/
public interface CompanyAuthenticationMapper extends BaseMapper<CompanyAuthentication> {

    @Update("UPDATE company_authentication SET result=#{result},check_status=#{checkStatus} " +
            "WHERE apply_id=#{applyId} AND check_status='未审核'")
    boolean auditAuthentication(AuditVO vo);

}




