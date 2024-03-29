package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.common.JobStatus;
import com.jbgz.pancakejob.dto.DraftDTO;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.mapper.*;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.JobDataVO;
import com.jbgz.pancakejob.vo.JobInfoVO;
import com.jbgz.pancakejob.vo.JobUpVO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author CSY0214
 * @description 针对表【job】的数据库操作Service实现
 * @createDate 2022-12-30 22:11:53
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
        implements JobService {
    @Resource
    private JobMapper jobMapper;
    @Resource
    private RecruiterMapper recruiterMapper;
    @Resource
    private JobTypeMapper jobTypeMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private NotificationService notificationService;

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
    public JobDTO getJobDTO(Job job) {
        try {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobId(job.getJobId());
            jobDTO.setRecruiterId(job.getRecruiterId());

            User user = userMapper.selectById(job.getRecruiterId());
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
            if (user.getScore() != null)
                jobDTO.setRecruiterScore(user.getScore());
            else
                jobDTO.setRecruiterScore(BigDecimal.ZERO);
            return jobDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<JobDTO> getJobListDTO(List<Job> jobList) {
        List<JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobList) {
            JobDTO jobDTO = getJobDTO(job);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    //获取已发布且审核通过的兼职列表
    public List<JobDTO> getJobList(String state) {
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<Job>();
        //筛选已发布且正在招聘的兼职
        //state没用上，前端需要多状态，lmq 2023/6/18
        jobQueryWrapper.eq("job_state", state);
        List<JobDTO> jobDTOList = getJobListDTO(jobMapper.selectList(jobQueryWrapper));
        return jobDTOList;
    }

    //获取招聘方管理的所有兼职
    public List<JobDTO> getAllJobList(int recruiterId) throws SelfDesignException {
        if(recruiterMapper.selectById(recruiterId) == null)
            throw new SelfDesignException("不存在该招聘方");
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<Job>();
        jobQueryWrapper.eq("recruiter_id", recruiterId);
        List<JobDTO> jobDTOList = getJobListDTO(jobMapper.selectList(jobQueryWrapper));
        return jobDTOList;
    }

    //获取招聘方的兼职草稿箱列表
    public List<DraftDTO> getJobDraftList(int recruiterId) {
        List<DraftDTO> draft = jobMapper.getDraftListById(recruiterId);
        return draft;
    }

    //获取单个兼职信息
    public List<JobDTO> getJobInfo(int jobId) throws SelfDesignException {
        Job job = jobMapper.selectById(jobId);
        if(job == null)
            throw new SelfDesignException("不存在该兼职信息");
        List<JobDTO> jobDTO = new ArrayList<>();
        jobDTO.add(getJobDTO(job));
        return jobDTO;
    }

    //发布兼职草稿
    public boolean upJobDraft(Integer jobId) {
        return jobMapper.alterJobState(jobId, "未发布", "未审核");
    }

    //发布兼职
    public boolean createJob(JobUpVO jobUpVO) throws SelfDesignException {
        if(jobUpVO.getJobInfo().getJobName()==null)
            throw new SelfDesignException("兼职名称不能为空");
        if(jobUpVO.getJobInfo().getJobType()==null)
            throw new SelfDesignException("兼职类型不能为空");
        if(jobUpVO.getJobInfo().getWorkDetails()==null)
            throw new SelfDesignException("兼职详情不能为空");
        if(jobUpVO.getJobInfo().getWorkTime()==null)
            throw new SelfDesignException("兼职每日时长不能为空");
        if(jobUpVO.getJobInfo().getStartTime()==null)
            throw new SelfDesignException("兼职开始时间不能为空");
        if (jobUpVO.getJobInfo().getEndTime()==null)
            throw new SelfDesignException("兼职结束时间不能为空");
        if(jobUpVO.getJobInfo().getWorkPlace()==null)
            throw new SelfDesignException("兼职地点不能为空");
        if (jobUpVO.getJobInfo().getSalary()==null)
            throw new SelfDesignException("兼职日薪资不能为空");
        try {
            JobInfoVO jobInfo = jobUpVO.getJobInfo();
            Job job = new Job();
            job.setRecruiterId(jobUpVO.getRecruiterId());
            job.setReleaseTime(new Date());
            if (jobUpVO.isIfRelease())
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

            int re = jobMapper.insert(job);
            System.out.println("insert:" + re);
            return re > 0;
        }catch (Exception e){
            System.out.print(e.getMessage());
            if(e.getClass()== DataIntegrityViolationException.class){
               if(e.getMessage().contains("FOREIGN KEY (`recruiter_id`)"))
                   throw new SelfDesignException("不存在该招聘方");
               if(e.getMessage().contains("FOREIGN KEY (`job_type`)"))
                   throw new SelfDesignException("不存在该兼职类型");
            }
        }
        return false;
    }

    //修改兼职草稿
    public boolean changeJobDraft(JobDataVO jobData) {
        return jobMapper.alterDraftContent(jobData);
    }

    //结束招聘
    public boolean closeRecruit(int jobId) throws SelfDesignException{
        Job job = jobMapper.selectById(jobId);
        if(job == null)
            throw new SelfDesignException("不存在该兼职信息");
        else if(!job.getJobState().equals("已通过"))
            throw new SelfDesignException("该兼职未在招聘");
        if (job.getAcceptedNum()>0 && job.getFinishedNum()>0 && job.getAcceptedNum().equals(job.getFinishedNum()))
            job.setJobState("已完成");
        else
            job.setJobState("已结束");
        int re = jobMapper.updateById(job);
        if(re>0){
            List<Integer> refuses = orderService.refuseRestJobhunter(jobId);
            return notificationService.noticeRestJobhunter(refuses);
        }
        return re > 0;
    }

    public boolean deleteJob(Integer jobId, String jobType) {
        return jobMapper.deleteJob(jobId, jobType);
    }

    public List<FavoritesDTO> getFavoritesDTO(Integer dirId, Integer jobhunterId) {
        return jobMapper.getFavorites(dirId, jobhunterId);
    }

    //修改兼职状态
    public boolean changeJobState(int jobId, String jobState) throws SelfDesignException {
        Job job = jobMapper.selectById(jobId);
        if(job == null)
            throw new SelfDesignException("不存在该兼职信息");
        if(jobState == null)
            throw new SelfDesignException("兼职状态为空");
        job.setJobState(jobState);
        int re = jobMapper.updateById(job);
        return re > 0;
    }

}




