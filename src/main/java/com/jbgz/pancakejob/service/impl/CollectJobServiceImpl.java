package com.jbgz.pancakejob.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.CollectJob;
import com.jbgz.pancakejob.service.CollectJobService;
import com.jbgz.pancakejob.mapper.CollectJobMapper;
import com.jbgz.pancakejob.vo.CollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【collect_job】的数据库操作Service实现
* @createDate 2022-12-30 22:38:39
*/
@Service
public class CollectJobServiceImpl extends ServiceImpl<CollectJobMapper, CollectJob>
    implements CollectJobService{

    @Autowired
    private CollectJobMapper collectMapper;

//    @Override
//    public List<Integer> findJob(Integer dirId) {
//        return null;
//    }


    //添加收藏
    public boolean addCollect(CollectVO vo){
        Date date = new Date();
        vo.setCollectTime(date);
        boolean tmp = collectMapper.insertCollect(vo);
        return tmp;
    }
    //修改收藏夹位置
    public boolean updatePosition(CollectVO vo){
        return  collectMapper.updatePosition(vo);
    }

    public boolean deleteCollect(Integer jobhunterId, Integer jobId){
        return collectMapper.deleteCollect(jobhunterId,jobId);
    }

    public boolean findIfExist(Integer jobhunterId, Integer jobId){
        List<Integer> finder = collectMapper.findIfExist(jobhunterId,jobId);
        if(finder.size()!=0) return true;
        return false;
    }

}




