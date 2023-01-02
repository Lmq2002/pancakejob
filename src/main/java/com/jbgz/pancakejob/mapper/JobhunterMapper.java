package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.entity.Administrator;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.GetPersonalInfoVO;
import com.jbgz.pancakejob.vo.PersonalInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author CSY0214
* @description 针对表【jobhunter】的数据库操作Mapper
* @createDate 2022-12-30 22:39:02
* @Entity com.jbgz.pancakejob.entity.Jobhunter
*/
public interface JobhunterMapper extends BaseMapper<Jobhunter> {

    @Select("SELECT DISTINCT nickname, headportrait, contact_method, email, introduction, birthday, school " +
            "FROM user AS a LEFT JOIN jobhunter AS b ON a.user_id = b.jobhunter_id " +
            "WHERE a.user_id = #{jobhunterId}")
    public PersonalInfoDTO getInfo(GetPersonalInfoVO vo);

    @Insert("INSERT INTO jobhunter (jobhunter_id) VALUES (#{jobhunterId})")
    Integer registJobhunter(Jobhunter jobhunter);

    @Update("UPDATE user INNER JOIN jobhunter ON user.user_id = jobhunter.jobhunter_id" +
            " SET" +
            " nickname = #{nickname}," +
            " school = #{school}," +
            " headportrait = #{headportrait},"+
            " contact_method = #{contactMethod},"+
            " introduction = #{introduction},"+
            " birthday = #{birthday}"+
            " WHERE" +
            " user_id = #{jobhunterId};")
    boolean updateInfo(PersonalInfoVO vo);
}




