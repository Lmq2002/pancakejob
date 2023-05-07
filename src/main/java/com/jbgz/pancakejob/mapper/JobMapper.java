package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.DraftDTO;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbgz.pancakejob.vo.JobDataVO;
import org.apache.ibatis.annotations.Delete;
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
    List<DraftDTO> getDraftListById(Integer recruiterId);

    @Update("UPDATE job SET job_state= #{newState} where job_state= #{oldState} AND job_id= #{jobId}")
    boolean alterJobState(Integer jobId, String oldState, String newState);

    @Update("UPDATE job SET work_name=#{jobdata.jobName},job_type=#{jobdata.jobType},work_details=#{jobdata.workDetails},"+
            "start_time=#{jobdata.startTime}, end_time=#{jobdata.endTime},work_place=#{jobdata.workPlace},salary=#{jobdata.salary}," +
            "applied_num=#{jobdata.employeeNum},work_time=#{jobdata.workTime} WHERE job_id=#{jobdata.jobId}")
    boolean alterDraftContent(JobDataVO jobData);

    @Select("SELECT job.job_id,work_name,work_place,start_time,end_time,work_time,salary,job_type " +
            "FROM job " +
            "JOIN favorites_dir JOIN collect_job " +
            "WHERE favorites_dir_id=#{dirId} AND " +
            "collect_job.job_id=job.job_id AND " +
            "collect_job.jobhunter_id=#{jobhunterId} AND " +
            "favorites_dir.favorites_dir_id = collect_job.collect_position")
    List<FavoritesDTO> getFavorites(Integer dirId, Integer jobhunterId);

    @Delete("DELETE FROM job WHERE job_id=#{jobId} AND job_state=#{jobType}")
    boolean deleteJob(Integer jobId, String jobType);
}




