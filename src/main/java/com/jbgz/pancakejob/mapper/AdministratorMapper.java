package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
* @author CSY0214
* @description 针对表【administrator】的数据库操作Mapper
* @createDate 2022-12-30 22:38:03
* @Entity com.jbgz.pancakejob.entity.Administrator
*/
public interface AdministratorMapper extends BaseMapper<Administrator> {

    @Insert("INSERT INTO administrator (admin_id,password) VALUES (#{adminId},#{password})")
    Integer registAdmin(Administrator admin);
}




