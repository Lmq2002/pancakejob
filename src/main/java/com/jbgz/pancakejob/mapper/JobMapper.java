package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【job】的数据库操作Mapper
* @createDate 2022-12-30 22:11:53
* @Entity com.jbgz.pancakejob.entity.Job
*/
public interface JobMapper extends BaseMapper<Job> {

//    @Select("")
    @Select("SELECT * FROM job WHERE job_state= '未发布' AND recruiter_id= #{recruiterId}")
    List<Job> getDraftListById(Integer recruiterId);

    @Update("UPDATE job SET job_state= #{newState} where job_state= #{oldState} AND job_id= #{jobId}")
    boolean alterJobState(Integer jobId, String oldState, String newState);

}




