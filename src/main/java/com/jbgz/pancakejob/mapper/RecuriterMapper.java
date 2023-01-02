package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.Administrator;
import com.jbgz.pancakejob.entity.Recuriter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
* @author CSY0214
* @description 针对表【recuriter】的数据库操作Mapper
* @createDate 2022-12-30 22:39:49
* @Entity com.jbgz.pancakejob.entity.Recuriter
*/
public interface RecuriterMapper extends BaseMapper<Recuriter> {
    @Insert("INSERT INTO recuriter (recuriter_id) VALUES (#{recuriterId})")
    Integer registRecuriter(Recuriter recuriter);
}




