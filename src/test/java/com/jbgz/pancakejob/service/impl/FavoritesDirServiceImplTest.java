package com.jbgz.pancakejob.service.impl;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.FavoritesDirDTO;
import com.jbgz.pancakejob.entity.FavoritesDir;
import com.jbgz.pancakejob.mapper.FavoritesDirMapper;
import com.jbgz.pancakejob.service.FavoritesDirService;
import com.jbgz.pancakejob.service.JobService;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Feature("Integration Testing")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class})
class FavoritesDirServiceImplTest {

    @Resource
    FavoritesDirService service;
    @Resource
    JobService jobService;

    @Resource
    FavoritesDirMapper favoritesDirMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Rollback(value = true)
    void IfParamanCorrect(){
        //stub
        jobService = mock(JobService.class);
        favoritesDirMapper = mock(FavoritesDirMapper.class);

        //data
        Integer jobhunterId=10014;
        List<FavoritesDirDTO> dirList = new ArrayList<>();
        FavoritesDirDTO dir1 = new FavoritesDirDTO();
        dir1.setFavoritesDirId(1);
        dirList.add(dir1);
        List<FavoritesDTO> favoritesList = new ArrayList<>();

        favoritesDirMapper.getDirList(jobhunterId);
        when(favoritesDirMapper.getDirList(jobhunterId)).thenReturn(dirList);

        List<FavoritesDTO> f=jobService.getFavoritesDTO(dir1.getFavoritesDirId(),jobhunterId);
        when(jobService.getFavoritesDTO(anyInt(),anyInt())).thenReturn(favoritesList);

        verify(favoritesDirMapper).getDirList(eq(jobhunterId));
        verify(jobService).getFavoritesDTO(eq(dir1.getFavoritesDirId()),eq(jobhunterId));

    }

    @AfterEach
    void tearDown() {
    }
}