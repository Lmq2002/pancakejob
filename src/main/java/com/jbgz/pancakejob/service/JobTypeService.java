package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.entity.JobType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.utils.SelfDesignException;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【job_type】的数据库操作Service
* @createDate 2022-12-30 22:19:17
*/
public interface JobTypeService extends IService<JobType> {

    List<JobType> getTypeList();

    int addJobType(String typeName) throws SelfDesignException;

    boolean deleteJobType(int typeId) throws SelfDesignException;

    int changeJobType(int typeId,String typeName) throws SelfDesignException;

}
