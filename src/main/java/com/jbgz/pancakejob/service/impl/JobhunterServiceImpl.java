package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.jbgz.pancakejob.service.JobhunterService;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.vo.GetPersonalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【jobhunter】的数据库操作Service实现
* @createDate 2022-12-30 22:39:02
*/
@Service
public class JobhunterServiceImpl extends ServiceImpl<JobhunterMapper, Jobhunter>
    implements JobhunterService{

    @Autowired
    private JobhunterMapper jobhunterMapper;

    @Override
    public PersonalInfoDTO getPersonalInfo(GetPersonalInfoVO vo) {
        return jobhunterMapper.getInfo(vo);
    }
}




