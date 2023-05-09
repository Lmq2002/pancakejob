package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.entity.CollectJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.CollectVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【collect_job】的数据库操作Service
* @createDate 2022-12-30 22:38:39
*/
public interface CollectJobService extends IService<CollectJob> {

    /**
     * 根据收藏夹id找到底下的所有收藏相应的jobId
     * 返回jobId列表
     * */
//    public List<Integer> findJob(Integer dirId);

    public boolean addCollect(CollectVO vo);
    public boolean updatePosition(CollectVO vo);
    public boolean deleteCollect(Integer jobhunterId, Integer jobId);
    public boolean findIfExist(Integer jobhunterId, Integer jobId);
}
