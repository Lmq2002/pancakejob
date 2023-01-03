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

import java.util.*;

/**
* @author CSY0214
* @description 针对表【favorites_dir】的数据库操作Service实现
* @createDate 2022-12-30 22:38:52
*/
@Service
public class FavoritesDirServiceImpl extends ServiceImpl<FavoritesDirMapper, FavoritesDir>
    implements FavoritesDirService{

    @Autowired
    private FavoritesDirMapper favoritesDirMapper;

    @Autowired
    private CollectJobMapper collectJobMapper;

//    @Autowired
//    private JobMapper jobMapper;

    @Autowired
    private JobService jobService;
    @Override
    public FavoritesDirDTO getDirInfo(Integer favoritesDirId) {
        FavoritesDirDTO dto = new FavoritesDirDTO();
        dto.setFavoritesDirId(favoritesDirId);
        dto.setFavoritesDirName(favoritesDirMapper.getDirName(favoritesDirId));
        return dto;

    }

//    @Override
//    public List<Integer> getDirIdList(Integer jobhunterId) {
//        return favoritesDirMapper.getDirIdList(jobhunterId);
//    }

    @Override
    public List<FavoritesDirDTO> getDirList(Integer jobhunterId) {
        List<Integer> dirIdList = favoritesDirMapper.getDirIdList(jobhunterId);
        List<FavoritesDirDTO> favoritesDirDTOList = new LinkedList<>();
        for(Integer dirId : dirIdList){ //n个收藏夹
            FavoritesDirDTO favoritesDirDTO = getDirInfo(dirId);

            List<Integer> jobIdList = collectJobMapper.getJobId(dirId);
            List<FavoritesDTO> favoritesDTOList = new LinkedList<>();
            for(Integer jobId : jobIdList){
                FavoritesDTO favoritesDTO =jobService.getFavorites(jobId);
                favoritesDTOList.add(favoritesDTO);
            }
            Map<String, List<FavoritesDTO>> map = new HashMap<>();
            map.put("favorites",favoritesDTOList);
            favoritesDirDTO.setFavorites(map);
            favoritesDirDTOList.add(favoritesDirDTO);
        }
        return  favoritesDirDTOList;
    }
}




