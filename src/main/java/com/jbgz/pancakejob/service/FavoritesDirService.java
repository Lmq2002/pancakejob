package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.FavoritesDirDTO;
import com.jbgz.pancakejob.entity.FavoritesDir;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【favorites_dir】的数据库操作Service
* @createDate 2022-12-30 22:38:52
*/
public interface FavoritesDirService extends IService<FavoritesDir> {
    /**
     * 根据收藏夹id获取
     * */
    public FavoritesDirDTO getDirInfo(Integer favoritesDirId);

//    public List<Integer> getDirIdList(Integer jobhunterId);

    /**
     * 根据求职者id获取收藏夹详细列表
     * */
    public List<FavoritesDirDTO> getDirList(Integer jobhunterId);
}
