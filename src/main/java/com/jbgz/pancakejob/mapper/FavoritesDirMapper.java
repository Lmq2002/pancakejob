package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.entity.FavoritesDir;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【favorites_dir】的数据库操作Mapper
* @createDate 2022-12-30 22:38:52
* @Entity com.jbgz.pancakejob.entity.FavoritesDir
*/
public interface FavoritesDirMapper extends BaseMapper<FavoritesDir> {

    @Select("SELECT DISTINCT favorites_dir_id FROM favorites_dir WHERE jobhunter_id = #{jobhunterId}")
    public List<Integer> getDirIdList(Integer jobhunterId);

    @Select("SELECT DISTINCT favorites_dir_name FROM favorites_dir WHERE favorites_dir_id = #{dirId}")
    public String getDirName(Integer dirId);
}




