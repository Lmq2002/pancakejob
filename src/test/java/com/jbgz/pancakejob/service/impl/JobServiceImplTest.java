package com.jbgz.pancakejob.service.impl;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.vo.JobInfoVO;
import com.jbgz.pancakejob.vo.JobUpVO;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = {PancakejobApplication.class,JobMapper.class})
@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = {"classpath:../../main/resources/mapper/JobMapper.xml"})
//@ContextConfiguration(locations = {"classpath:../../main/java/com/jbgz/pancakejob/service/impl/JobServiceImpl.java"})
class JobServiceImplTest {

    @Autowired
static JobService jobService;
JobUpVO jobUpVO;
JobInfoVO jobInfoVO;
    @BeforeAll
    public static void setUpAll(){
        jobService = new JobServiceImpl();

    }
    @BeforeEach
    void setUp(){
//        jobService = new JobServiceImpl();
        jobUpVO = new JobUpVO();
        jobInfoVO = new JobInfoVO();
    }
    @Test
    @Rollback(false)
    void createJobByNullJobName() {
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName(null);
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(BigDecimal.valueOf(800.00));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);

        assertFalse(jobService.createJob(jobUpVO));

    }

    @AfterEach
    void tearDown(){
        jobUpVO = null;
        jobInfoVO = null;
//        jobService = null;
    }
    @AfterAll
    static public void deleteAll(){
        jobService = null;
    }
}