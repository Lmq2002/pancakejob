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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
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

    @InjectMocks private FavoritesDirServiceImpl service;
    @Mock
    private JobServiceImpl jobService;

    @Mock
    FavoritesDirMapper favoritesDirMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(service);
    }

    @Test
    @Rollback(value = true)
    void IfParamanCorrect(){

            // Set up test data
            Integer jobhunterId = 10014;
            List<FavoritesDirDTO> dirList = new ArrayList<>();
            FavoritesDirDTO dir1 = new FavoritesDirDTO();
            dir1.setFavoritesDirId(1);
            dirList.add(dir1);
            FavoritesDirDTO dir2 = new FavoritesDirDTO();
            dir2.setFavoritesDirId(2);
            dirList.add(dir2);

            // Mock the dependencies
            when(favoritesDirMapper.getDirList(eq(jobhunterId))).thenReturn(dirList);
            when(jobService.getFavoritesDTO(anyInt(), eq(jobhunterId))).thenReturn(new ArrayList<>());

            // Call the method under test
            List<FavoritesDirDTO> result = service.getDirList(jobhunterId);

            // Verify the interactions
            ArgumentCaptor<Integer> dirIdCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> jobhunterIdCaptor = ArgumentCaptor.forClass(Integer.class);
            verify(jobService, times(dirList.size())).getFavoritesDTO(dirIdCaptor.capture(), jobhunterIdCaptor.capture());

            List<Integer> capturedDirIds = dirIdCaptor.getAllValues();
            List<Integer> capturedJobhunterIds = jobhunterIdCaptor.getAllValues();

            // Assert that the captured values match the expected values
            for (int i = 0; i < dirList.size(); i++) {
                assertEquals(dirList.get(i).getFavoritesDirId(), capturedDirIds.get(i));
                assertEquals(jobhunterId, capturedJobhunterIds.get(i));
            }

            // Assert the result
            assertEquals(dirList, result);
        }

//        favoritesDirMapper.getDirList(jobhunterId);
//        when(favoritesDirMapper.getDirList(jobhunterId)).thenReturn(dirList);
//
//        List<FavoritesDTO> f=jobService.getFavoritesDTO(dir1.getFavoritesDirId(),jobhunterId);
//        when(jobService.getFavoritesDTO(anyInt(),anyInt())).thenReturn(favoritesList);
//
//        verify(favoritesDirMapper).getDirList(eq(jobhunterId));
//        verify(jobService).getFavoritesDTO(eq(dir1.getFavoritesDirId()),eq(jobhunterId));



    @AfterEach
    void tearDown() {
    }
}