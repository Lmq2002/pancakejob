package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.RecruiterPersonInfoDTO;
import com.jbgz.pancakejob.entity.Recruiter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.RecruiterPersonInfoVO;

/**
* @author CSY0214
* @description 针对表【recuriter】的数据库操作Service
* @createDate 2022-12-30 22:39:49
*/
public interface RecruiterService extends IService<Recruiter> {
    public RecruiterPersonInfoDTO getInfo(Integer recuriterId);

    public boolean setInfo(RecruiterPersonInfoVO vo);
}
