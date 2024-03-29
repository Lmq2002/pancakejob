package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.CompanyAuthenDTO;
import com.jbgz.pancakejob.dto.PersonAuthenDTO;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.AuditVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【company_authentication】的数据库操作Mapper
* @createDate 2022-12-30 22:38:45
* @Entity com.jbgz.pancakejob.entity.CompanyAuthentication
*/
public interface CompanyAuthenticationMapper extends BaseMapper<CompanyAuthentication> {

    @Update("UPDATE company_authentication SET result=#{result},check_status=#{checkStatus},check_time=#{checkTime} " +
            "WHERE apply_id=#{applyId} AND check_status='未审核'")
    boolean auditAuthentication(AuditVO vo);

    @Select("SELECT * FROM company_authentication")
    List<CompanyAuthenDTO> getList();

    @Select("SELECT * FROM company_authentication WHERE recruiter_id=#{recruiterId}")
    List<CompanyAuthenDTO> getOne(Integer recruiterId);

    @Select("SELECT apply_id, company_name, company_id, company_type, certification," +
            "apply_time,check_status, check_time, result " +
            "FROM company_authentication WHERE recruiter_id=#{recruiterId} " +
            "ORDER BY apply_time DESC LIMIT 1")
    List<CompanyAuthenDTO> getNewestOne(Integer recruiterId);

}




