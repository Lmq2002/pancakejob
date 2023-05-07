package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.PersonAuthenDTO;
import com.jbgz.pancakejob.entity.RealnameAuthentication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.AuditVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【realname_authentication】的数据库操作Mapper
* @createDate 2022-12-30 22:39:44
* @Entity com.jbgz.pancakejob.entity.RealnameAuthentication
*/
public interface RealnameAuthenticationMapper extends BaseMapper<RealnameAuthentication> {

    @Update("UPDATE realname_authentication SET result=#{result},check_status=#{checkStatus} " +
            "WHERE apply_id=#{applyId} AND check_status='未审核'")
    boolean auditAuthentication(AuditVO vo);

    @Select("SELECT * FROM realname_authentication")
    List<PersonAuthenDTO> getList();

    @Select("SELECT * FROM realname_authentication WHERE jobhunter_id=#{jobhunterId}")
    List<PersonAuthenDTO> getOne(Integer jobhunterId);
}




