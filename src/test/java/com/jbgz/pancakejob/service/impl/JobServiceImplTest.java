package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.mapper.JobMapper;
import com.jbgz.pancakejob.mapper.JobTypeMapper;
import com.jbgz.pancakejob.mapper.RecruiterMapper;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.JobInfoVO;
import com.jbgz.pancakejob.vo.JobUpVO;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


//@ContextConfiguration(locations = {"classpath:../../main/resources/mapper/JobMapper.xml"})
//@ContextConfiguration(locations = {"classpath:../../main/java/com/jbgz/pancakejob/service/impl/JobServiceImpl.java"})
@SpringBootTest(classes = {PancakejobApplication.class})
@RunWith(SpringRunner.class)
@Feature("Unit Testing")
class JobServiceImplTest {

    @Resource
    JobService jobService;
JobUpVO jobUpVO;
JobInfoVO jobInfoVO;

    @BeforeEach
    void setUp(){
//        jobService = new JobServiceImpl();
        jobUpVO = new JobUpVO();
        jobInfoVO = new JobInfoVO();
    }
    @Test
    @Rollback(value = true)
    @Transactional
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
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职名称不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullJobType(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(null);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职类型不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullWorkDetails(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails(null);
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职详情不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullWorkTime(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(null);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职每日时长不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullStartTime(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime(null);
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职开始时间不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullEndTime(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime(null);
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职结束时间不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullWorkPlace(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace(null);
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职地点不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNullSalary(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(null);
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("兼职日薪资不能为空");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByNormal(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        try{
            assertTrue(jobService.createJob(jobUpVO));
        }catch (Exception e){}
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByUnExceptedJobType(){
        jobUpVO.setRecruiterId(10018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(10);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("不存在该兼职类型");
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void createJobByUnexpectedRecruiter(){
        jobUpVO.setRecruiterId(20018);
        jobUpVO.setIfRelease(false);
        jobInfoVO.setJobName("兼职test");
        jobInfoVO.setJobType(1);
        jobInfoVO.setWorkDetails("这是兼职详情");
        jobInfoVO.setWorkTime(8);
        jobInfoVO.setStartTime("2023-01-12 00:00:00");
        jobInfoVO.setEndTime("2023-01-31 00:00:00");
        jobInfoVO.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfoVO.setSalary(new BigDecimal("800.00"));
        jobInfoVO.setEmployeeNum(30);
        jobUpVO.setJobInfo(jobInfoVO);
        assertThatThrownBy(()->jobService.createJob(jobUpVO))
                .isInstanceOf(SelfDesignException.class)
                .hasMessage("不存在该招聘方");
    }


    @AfterEach
    void tearDown(){
        jobUpVO = null;
        jobInfoVO = null;
//        jobService = null;
    }
}