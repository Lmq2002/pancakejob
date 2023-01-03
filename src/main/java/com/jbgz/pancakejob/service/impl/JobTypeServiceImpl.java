package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.service.JobTypeService;
import com.jbgz.pancakejob.mapper.JobTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【job_type】的数据库操作Service实现
* @createDate 2022-12-30 22:19:17
*/
@Service
public class JobTypeServiceImpl extends ServiceImpl<JobTypeMapper, JobType>
    implements JobTypeService{

    @Resource
    private JobTypeMapper jobTypeMapper;

    //获取所有兼职类型列表
    public List<JobType> getTypeList(){
        QueryWrapper<JobType> typeQueryWrapper=new QueryWrapper<JobType>();
        typeQueryWrapper.orderByDesc("type_id");
        List<JobType> jobTypeList=jobTypeMapper.selectList(typeQueryWrapper);
        return jobTypeList;
    }

    //增加兼职类型
    public int addJobType(String typeName){
        JobType jobType=new JobType();
        jobType.setTypeName(typeName);
        QueryWrapper<JobType> jobTypeWrapper=new QueryWrapper<JobType>();
        jobTypeWrapper.eq("type_name",typeName);
        if(jobTypeMapper.selectCount(jobTypeWrapper)>0)
            return -1;
        else{
            int re=jobTypeMapper.insert(jobType);
            System.out.println("insert:"+re);
            return re;
        }
    }

    //删除兼职类型
    public boolean deleteJobType(int typeId){
        int re=jobTypeMapper.deleteById(typeId);
        System.out.println("delete:"+re);
        return re>0;
    }

    //修改兼职类型
    public int changeJobType(int typeId,String typeName){
        JobType jobType=new JobType();
        jobType.setTypeId(typeId);
        jobType.setTypeName(typeName);
        QueryWrapper<JobType> jobTypeWrapper=new QueryWrapper<JobType>();
        jobTypeWrapper.eq("type_name",typeName);
        if(jobTypeMapper.selectCount(jobTypeWrapper)>0)
            return -1;
        else {
            int re = jobTypeMapper.updateById(jobType);
            System.out.println("update:" + re);
            return re;
        }
    }
}




