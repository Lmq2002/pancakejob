package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Administrator;
import com.jbgz.pancakejob.service.AdministratorService;
import com.jbgz.pancakejob.mapper.AdministratorMapper;
import org.springframework.stereotype.Service;

/**
* @author CSY0214
* @description 针对表【administrator】的数据库操作Service实现
* @createDate 2022-12-30 22:38:03
*/
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator>
    implements AdministratorService{

}




