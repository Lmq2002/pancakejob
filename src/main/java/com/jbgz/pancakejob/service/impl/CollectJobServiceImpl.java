package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.CollectJob;
import com.jbgz.pancakejob.service.CollectJobService;
import com.jbgz.pancakejob.mapper.CollectJobMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【collect_job】的数据库操作Service实现
* @createDate 2022-12-30 22:38:39
*/
@Service
public class CollectJobServiceImpl extends ServiceImpl<CollectJobMapper, CollectJob>
    implements CollectJobService{

//    @Override
//    public List<Integer> findJob(Integer dirId) {
//        return null;
//    }
}




