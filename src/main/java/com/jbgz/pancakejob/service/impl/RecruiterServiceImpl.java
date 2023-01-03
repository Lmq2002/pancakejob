package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.RecruiterPersonInfoDTO;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.service.RecruiterService;
import com.jbgz.pancakejob.mapper.RecruiterMapper;
import com.jbgz.pancakejob.vo.RecruiterPersonInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【recruiter】的数据库操作Service实现
* @createDate 2022-12-30 22:39:49
*/
@Service
public class RecruiterServiceImpl extends ServiceImpl<RecruiterMapper, Recruiter>
    implements RecruiterService {

    @Autowired
    RecruiterMapper mapper;
    @Override
    public RecruiterPersonInfoDTO getInfo(Integer recruiterId) {
        return mapper.getInfo(recruiterId);
    }

    @Override
    public boolean setInfo(RecruiterPersonInfoVO vo) {
        return mapper.updateInfo(vo);
    }
}




