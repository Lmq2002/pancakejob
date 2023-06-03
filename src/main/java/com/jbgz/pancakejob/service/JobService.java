package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.common.JobStatus;
import com.jbgz.pancakejob.dto.DraftDTO;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.JobDataVO;
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
    List<DraftDTO> getJobDraftList(int recruiterId) throws SelfDesignException;
    List<JobDTO> getAllJobList(int recruiterId) throws SelfDesignException;
    List<JobDTO> getJobInfo(int jobId) throws SelfDesignException;
    List<FavoritesDTO> getFavoritesDTO(Integer dirId, Integer jobhunterId);
    boolean upJobDraft(Integer jobId);
    boolean createJob(JobUpVO jobUpVO);
    boolean changeJobDraft(JobDataVO jobData);
    boolean closeRecruit(int jobId) throws SelfDesignException;
    boolean changeJobState(int jobId, String jobState) throws SelfDesignException;
    boolean deleteJob(Integer jobId, String jobType);

}
