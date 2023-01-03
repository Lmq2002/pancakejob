package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.CollectJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【collect_job】的数据库操作Mapper
* @createDate 2022-12-30 22:38:39
* @Entity com.jbgz.pancakejob.entity.CollectJob
*/
public interface CollectJobMapper extends BaseMapper<CollectJob> {

    @Select("SELECT DISTINCT job_id FROM collect_job WHERE collect_position = #{dirId}")
    public List<Integer> getJobId(Integer dirId);
}




