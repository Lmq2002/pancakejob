package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.common.JobStatus;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.mapper.JobTypeMapper;
import com.jbgz.pancakejob.mapper.OrderMapper;
import com.jbgz.pancakejob.mapper.RecruiterMapper;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.vo.JobDataVO;
import com.jbgz.pancakejob.vo.JobInfoVO;
import com.jbgz.pancakejob.vo.JobUpVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【job】的数据库操作Service实现
* @createDate 2022-12-30 22:11:53
*/
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
    implements JobService{
    @Resource
    private JobMapper jobMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private RecruiterMapper recruiterMapper;
    @Resource
    private JobTypeMapper jobTypeMapper;

//    @Override
//    public ResultData getJobList(int pageNum, int pageSize){
//
//        ResultData result=new ResultData();
//        Page<Job> page =new Page<>(pageNum,pageSize);
//        QueryWrapper<Job> jobQueryWrapper= new QueryWrapper<Job>();
//        IPage iPage=jobMapper.selectPage(page,jobQueryWrapper);
//        List<Job> jobList= iPage.getRecords();
//        result.data.put("activity_list", jobList);
//        result.data.put("total", iPage.getTotal());
//        result.data.put("count", iPage.getRecords().size());
//        return result;
////        Page<VolActivity> page = new Page<>(pageNum, pageSize);
////        QueryWrapper<VolActivity> wrapper = new QueryWrapper<VolActivity>();
////        IPage iPage = volActivityMapper.selectPage(page,wrapper);
////        List<VolActivityDTO> dtoList=volActivityService.cutIntoVolActivityDTOList((List<VolActivity>)iPage.getRecords());
////        return jobMapper.selectList(null);
//    }
    public JobDTO getJObDTO(Job job){
        try {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobId(job.getJobId());
            jobDTO.setRecruiterId(job.getRecruiterId());

            Recruiter recruiter = recruiterMapper.selectById(job.getRecruiterId());
            jobDTO.setCompanyName(recruiter.getCompanyName());

            jobDTO.setReleaseTime(DateTimeTrans.dateToString(job.getReleaseTime()));
            jobDTO.setJobState(job.getJobState());

            if (job.getJobType() != null) {
                JobType jobType = jobTypeMapper.selectById(job.getJobType());
                jobDTO.setJobType(jobType.getTypeName());
            } else
                jobDTO.setJobType(null);

            jobDTO.setWorkName(job.getWorkName());
            jobDTO.setWorkTime(job.getWorkTime());
            jobDTO.setWorkPlace(job.getWorkPlace());
            jobDTO.setWorkDetails(job.getWorkDetails());
            jobDTO.setSalary(job.getSalary());

            jobDTO.setStartTime(DateTimeTrans.dateToString(job.getStartTime()));
            jobDTO.setEndTime(DateTimeTrans.dateToString(job.getEndTime()));
            return jobDTO;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<JobDTO> getJobListDTO(List<Job> jobList){
        List<JobDTO> jobDTOList=new ArrayList<>();
        for(Job job : jobList){
            JobDTO jobDTO=getJObDTO(job);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    //获取已发布且审核通过的兼职列表
    public List<JobDTO> getJobList(String state){
        QueryWrapper<Job> jobQueryWrapper= new QueryWrapper<Job>();
        //筛选已发布且正在招聘的兼职
        jobQueryWrapper.eq("job_state",state);
        List<JobDTO> jobDTOList=getJobListDTO(jobMapper.selectList(jobQueryWrapper));
        return jobDTOList;
    }

    //获取招聘方管理的所有兼职
    public List<JobDTO> getAllJobList(int recruiterId){
        QueryWrapper<Job> jobQueryWrapper=new QueryWrapper<Job>();
        jobQueryWrapper.eq("recruiter_id",recruiterId);
        List<JobDTO> jobDTOList=getJobListDTO(jobMapper.selectList(jobQueryWrapper));
        return jobDTOList;
    }

    //获取招聘方的兼职草稿箱列表
    public List<JobDTO> getJobDraftList(int recruiterId){
        List<Job> draft = jobMapper.getDraftListById(recruiterId);
        return getJobListDTO(draft);
    }

    //获取单个兼职信息
    public List<JobDTO> getJobInfo(int jobId){
        Job job=jobMapper.selectById(jobId);
        List<JobDTO> jobDTO=new ArrayList<>();
        jobDTO.add(getJObDTO(job));
        return jobDTO;
    }

    //发布兼职草稿
    public boolean upJobDraft(Integer jobId){
        return jobMapper.alterJobState(jobId, "未发布","未审核");
    }
    //发布兼职
    public boolean createJob(JobUpVO jobUpVO){
        JobInfoVO jobInfo=jobUpVO.getJobInfo();
        Job job=new Job();
        job.setRecruiterId(jobUpVO.getRecruiterId());
        job.setReleaseTime(new Date());
        if(jobUpVO.isIfRelease())
            job.setJobState("未审核");
        else
            job.setJobState("未发布");
        job.setJobType(jobInfo.getJobType());
        job.setWorkName(jobInfo.getJobName());
        job.setWorkTime(jobInfo.getWorkTime());
        job.setWorkPlace(jobInfo.getWorkPlace());
        job.setWorkDetails(jobInfo.getWorkDetails());
        job.setSalary(jobInfo.getSalary());
        job.setStartTime(DateTimeTrans.stringToDate(jobInfo.getStartTime()));
        job.setEndTime(DateTimeTrans.stringToDate(jobInfo.getEndTime()));
        job.setWorkerNum(jobInfo.getEmployeeNum());

        int re=jobMapper.insert(job);
        System.out.println("insert:"+re);
        return re>0;
    }

    //修改兼职草稿
    public boolean changeJobDraft(JobDataVO jobData){
        return jobMapper.alterDraftContent(jobData);
    }
    //结束招聘
    public boolean closeRecruit(int jobId){
        Job job=jobMapper.selectById(jobId);
        job.setJobState("已结束");
        int re=jobMapper.updateById(job);
        return re>0;
    }

    public boolean deleteJob(Integer jobId, String jobType){
        return jobMapper.deleteJob(jobId, jobType);
    }

    public List<FavoritesDTO> getFavoritesDTO(Integer dirId, Integer jobhunterId){
        return jobMapper.getFavorites(dirId,jobhunterId);
    }
}




