package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.vo.GetPersonalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author CSY0214
* @description 针对表【jobhunter】的数据库操作Service
* @createDate 2022-12-30 22:39:02
*/
public interface JobhunterService extends IService<Jobhunter> {



    /***
     * 根据user_id获取个人信息，需要查两张表
     * 返回：PersonalInfo
     */
    public PersonalInfoDTO getPersonalInfo(GetPersonalInfoVO vo);
}
