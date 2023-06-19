package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.FavoritesDirDTO;
import com.jbgz.pancakejob.entity.FavoritesDir;
import com.jbgz.pancakejob.mapper.CollectJobMapper;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.service.CollectJobService;
import com.jbgz.pancakejob.service.FavoritesDirService;
import com.jbgz.pancakejob.mapper.FavoritesDirMapper;
import com.jbgz.pancakejob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* @author CSY0214
* @description 针对表【favorites_dir】的数据库操作Service实现
* @createDate 2022-12-30 22:38:52
*/
@Service
public class FavoritesDirServiceImpl extends ServiceImpl<FavoritesDirMapper, FavoritesDir>
    implements FavoritesDirService{

    @Resource
    private FavoritesDirMapper favoritesDirMapper;

//    @Autowired
//    private CollectJobMapper collectJobMapper;

//    @Autowired
//    private JobMapper jobMapper;

    @Resource
    private JobService jobService;

//    @Override
//    public List<Integer> getDirIdList(Integer jobhunterId) {
//        return favoritesDirMapper.getDirIdList(jobhunterId);
//    }

    @Override
    public List<FavoritesDirDTO> getDirList(Integer jobhunterId) {
        //收藏夹列表
        List<FavoritesDirDTO> dirList = favoritesDirMapper.getDirList(jobhunterId);

        for(FavoritesDirDTO dir : dirList){ //n个收藏夹
            List<FavoritesDTO> f=jobService.getFavoritesDTO(dir.getFavoritesDirId(),jobhunterId);
            //Map<String, List<FavoritesDTO>> favoritesList = new HashMap<>();
            //favoritesList.put("favorites",jobService.getFavoritesDTO(dir.getFavoritesDirId(),jobhunterId));
            dir.setFavorites(f);
        }
        return  dirList;
    }

    @Override
    public boolean addDir(Integer jobhunterId, String dirName){
        return favoritesDirMapper.insertDir(jobhunterId,dirName,new Date());
    }

    @Override
    public boolean deleteDir(Integer jobhunterId, Integer dirId){
        return favoritesDirMapper.deleteDir(jobhunterId,dirId);
    }

    @Override
    public boolean updateDirName(Integer jobhunterId, String dirName, Integer dirId){
        return favoritesDirMapper.updateDirName(jobhunterId,dirName,dirId);
    }
}




