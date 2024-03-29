package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.PersonalInfoDTO;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.jbgz.pancakejob.service.JobhunterService;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.vo.GetPersonalInfoVO;
import com.jbgz.pancakejob.vo.PersonalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public List<PersonalInfoDTO> getPersonalInfo(GetPersonalInfoVO vo) {
        try {
            if (vo.getJobhunterId() != null) {
                PersonalInfoDTO dto = jobhunterMapper.getInfo(vo);
                List<PersonalInfoDTO> list = new ArrayList<>();
                list.add(dto);
                return list;
            } else
                return jobhunterMapper.getListAll();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean setPersonalInfo(PersonalInfoVO vo) {
        return jobhunterMapper.updateInfo(vo);
    }
}




