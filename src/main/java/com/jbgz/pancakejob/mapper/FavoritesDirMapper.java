package com.jbgz.pancakejob.mapper;

import com.jbgz.pancakejob.dto.FavoritesDirDTO;
import com.jbgz.pancakejob.entity.FavoritesDir;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【favorites_dir】的数据库操作Mapper
* @createDate 2022-12-30 22:38:52
* @Entity com.jbgz.pancakejob.entity.FavoritesDir
*/
public interface FavoritesDirMapper extends BaseMapper<FavoritesDir> {

    @Select("SELECT favorites_dir_id, favorites_dir_name FROM favorites_dir WHERE jobhunter_id=#{jobhunterId}")
    public List<FavoritesDirDTO> getDirList(Integer jobhunterId);

    @Select("SELECT DISTINCT favorites_dir_name FROM favorites_dir WHERE favorites_dir_id = #{dirId}")
    public String getDirName(Integer dirId);

    @Insert("INSERT INTO favorites_dir(jobhunter_id, favorites_dir_name,create_time) " +
            "VALUES(10014,'??','2023-01-01 12:12:12')")
    public boolean insertDir(Integer jobhunterId, String dirName, Date date);

    @Delete("DELETE FROM favorites_dir " +
            "WHERE jobhunter_id=#{jobhunterId} AND favorites_dir_id=#{dirId}")
    boolean deleteDir(Integer jobhunterId, Integer dirId);

    @Update("UPDATE favorites_dir SET favorites_dir_name=#{dirName} " +
            "WHERE jobhunter_id=#{jobhunterId} AND favorites_dir_id=#{dirId}")
    boolean updateDirName(Integer jobhunterId, String dirName, Integer dirId);
}




