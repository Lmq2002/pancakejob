package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.entity.Recuriter;
import com.jbgz.pancakejob.mapper.JobTypeMapper;
import com.jbgz.pancakejob.mapper.RecuriterMapper;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.utils.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private RecuriterMapper recuriterMapper;
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
        JobDTO jobDTO=new JobDTO();
        jobDTO.setJobId(job.getJobId());

        Recuriter recuriter = recuriterMapper.selectById(job.getRecuriterId());
        jobDTO.setCompanyName(recuriter.getCompanyName());

        jobDTO.setReleaseTime(DateTimeTrans.dateToString(job.getReleaseTime()));
        jobDTO.setJobState(job.getJobState());

        JobType jobType= jobTypeMapper.selectById(job.getJobType());
        jobDTO.setJobType(jobType.getTypeName());

        jobDTO.setWorkName(job.getWorkName());
        jobDTO.setWorkTime(DateTimeTrans.timeToString(job.getWorkTime()));

        jobDTO.setWorkPlace(job.getWorkPlace());
        jobDTO.setWorkDetails(job.getWorkDetails());
        jobDTO.setSalary(job.getSalary().doubleValue());

        jobDTO.setStartTime(DateTimeTrans.dateToString(job.getStartTime()));
        jobDTO.setEndTime(DateTimeTrans.dateToString(job.getEndTime()));
        return jobDTO;
    }

    public List<JobDTO> getJobListDTO(List<Job> jobList){
        List<JobDTO> jobDTOList=new ArrayList<>();
        for(Job job : jobList){
            JobDTO jobDTO=getJObDTO(job);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    public ResultData getJobList(){
        ResultData result=new ResultData();
        //QueryWrapper<Job> jobQueryWrapper= new QueryWrapper<Job>();
        List<JobDTO> jobDTOList=getJobListDTO(jobMapper.selectList(null));
        if(jobDTOList.size()!=0)
            result.data.put("job_list", jobDTOList);
        result.message = "请求成功";
        result.code = 200;
        return result;
    }

    public ResultData getJobInfo(int jobId){
        ResultData result=new ResultData();
        Job job=jobMapper.selectById(jobId);
        List<JobDTO> jobDTO=new ArrayList<>();
        jobDTO.add(getJObDTO(job));
        result.data.put("job_list",jobDTO);
        result.message = "请求成功";
        result.code = 200;
        return result;
    }


}




