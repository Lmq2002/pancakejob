package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.RecruiterPersonInfoDTO;
import com.jbgz.pancakejob.entity.Recruiter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.RecruiterPersonInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author CSY0214
* @description 针对表【recuriter】的数据库操作Mapper
* @createDate 2022-12-30 22:39:49
* @Entity com.jbgz.pancakejob.entity.Recruiter
*/
public interface RecruiterMapper extends BaseMapper<Recruiter> {
    @Insert("INSERT INTO recruiter (recruiter_id) VALUES (#{recruiterId})")
    Integer registRecruiter(Recruiter recruiter);

    @Select("SELECT DISTINCT recruiter_id, company_name, nickname, headportrait, contact_method, email, introduction"+
    " FROM user AS a INNER JOIN recruiter AS b ON a.user_id = b.recruiter_id"+
    " WHERE b.recruiter_id = #{recruiterId}")
    public RecruiterPersonInfoDTO getInfo(Integer recruiterId);

    @Update("UPDATE user "+
    " SET nickname = #{nickname},"+
    " headportrait = #{headportrait},"+
    " contact_method = #{contactMethod},"+
    " introduction = #{introduction}"+
    " WHERE user_id  = #{recruiterId}")
    public boolean updateInfo(RecruiterPersonInfoVO vo);
}




