package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.service.JobTypeService;
import com.jbgz.pancakejob.mapper.JobTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【job_type】的数据库操作Service实现
* @createDate 2022-12-30 22:19:17
*/
@Service
public class JobTypeServiceImpl extends ServiceImpl<JobTypeMapper, JobType>
    implements JobTypeService{

}




