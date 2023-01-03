package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.JobUpVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【job】的数据库操作Service
* @createDate 2022-12-30 22:11:53
*/
public interface JobService extends IService<Job> {
    //List<Job> getJobList(int pageNum, int pageSize);
    //ResultData getJobList(int pageNum,int pageSize);
    JobDTO getJObDTO(Job job);
    List<JobDTO> getJobListDTO(List<Job> jobList);
    List<JobDTO> getJobList(String state);
    List<JobDTO> getAllJobList(int recruiterId);
    List<JobDTO> getJobInfo(int jobId);
    boolean createJob(JobUpVO jobUpVO);
    boolean closeRecruit(int jobId);

    /**
     * 根据jobId获取收藏展示信息。
     * 返回对应收藏信息
     * */
    FavoritesDTO getFavorites(Integer jobId);
}
