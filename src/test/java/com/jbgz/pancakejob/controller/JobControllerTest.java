package com.jbgz.pancakejob.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.DraftDTO;
import com.jbgz.pancakejob.dto.FavoritesDTO;
import com.jbgz.pancakejob.dto.JobDTO;
import com.jbgz.pancakejob.entity.Job;
import com.jbgz.pancakejob.service.JobService;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.service.impl.JobServiceImpl;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.JobDataVO;
import com.jbgz.pancakejob.vo.JobInfoVO;
import com.jbgz.pancakejob.vo.JobUpVO;
import com.jbgz.pancakejob.vo.ReportVO;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@Feature("Integration Testing")
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void NullIDWhenGetJobList() throws Exception {
        String jobId="";

        mockMvc.perform(get("/job/getJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("jobId", jobId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职ID为空"));
    }

    @Test
    void UnexistIDWhenGetJobList() throws Exception {
        String jobId="100";

        mockMvc.perform(get("/job/getJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jobId", jobId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("不存在该兼职信息"));
    }

    @Test
    void NormalGetJobList() throws Exception {
        String jobId="-1";

        mockMvc.perform(get("/job/getJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jobId", jobId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("请求成功"));
    }

    @Test
    void NullIDWhenCloseRecruit() throws Exception {
        String jobId=null;

        mockMvc.perform(put("/job/closeRecruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jobId", jobId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职ID为空"));
    }

    @Test
    void UnexistIDWhenCloseRecruit() throws Exception {
        String jobId="100";

        mockMvc.perform(put("/job/closeRecruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jobId", jobId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("不存在该兼职信息"));
    }

    @Test
    void NormalCloseRecruit() throws Exception {
        String jobId="27";

        mockMvc.perform(put("/job/closeRecruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jobId", jobId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("结束招聘成功"));
    }

    @Test
    void NullIDWhenGetAllJobList() throws Exception {
        String recruiterId=null;

        mockMvc.perform(get("/job/getAllJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("recruiterId", recruiterId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("招聘方ID为空"));
    }

    @Test
    void UnexistIDWhenGetAllJobList() throws Exception {
        String recruiterId="200";

        mockMvc.perform(get("/job/getAllJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("recruiterId", recruiterId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("不存在该招聘方"));
    }

    @Test
    void NormalGetAllJobList() throws Exception {
        String recruiterId="10018";

        mockMvc.perform(get("/job/getAllJobList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("recruiterId", recruiterId))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("请求成功"));
    }

    @Test
    void NullJobIdWhenReportJob() throws Exception {
        ReportVO vo = new ReportVO();
        vo.setJobId(null);
        vo.setJobhunterId(10014);
        vo.setReason("此兼职信息不实");

        mockMvc.perform(post("/job/reportJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职ID为空"));
    }

    @Test
    void NullJobhunterIdWhenReportJob() throws Exception {
        ReportVO vo = new ReportVO();
        vo.setJobId(6);
        vo.setJobhunterId(null);
        vo.setReason("此兼职信息不实");

        mockMvc.perform(post("/job/reportJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("求职者ID为空"));
    }

    @Test
    void NullReasonWhenReportJob() throws Exception {
        ReportVO vo = new ReportVO();
        vo.setJobId(6);
        vo.setJobhunterId(10014);
        vo.setReason(null);

        mockMvc.perform(post("/job/reportJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("举报理由为空"));
    }

    @Test
    void NormalReportJob() throws Exception {
        ReportVO vo = new ReportVO();
        vo.setJobId(1);
        vo.setJobhunterId(10009);
        vo.setReason("此兼职信息不实");

        mockMvc.perform(post("/job/reportJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("举报成功"));
    }

    @Test
    void NullRecruiterIdWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(null);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("招聘方ID为空"));
    }

    @Test
    void NullJobNameWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName(null);
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职名称不能为空"));
    }

    @Test
    void NullWorkTimeWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(null);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职每日时长不能为空"));
    }

    @Test
    void NullStartTimeWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime(null);
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职开始时间不能为空"));
    }

    @Test
    void NullEndTimeWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime(null);
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职结束时间不能为空"));
    }

    @Test
    void NullWorkPlaceWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace(null);
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职地点不能为空"));
    }

    @Test
    void NullSalaryWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(null);
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职日薪资不能为空"));
    }

    @Test
    void NullWorkDetailsWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails(null);
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("兼职详情不能为空"));
    }

    @Test
    void UnexistRecruiterWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(20017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("不存在该招聘方"));
    }

    @Test
    void UnexistJobTypeIdWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10017);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(20);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("不存在该兼职类型"));
    }

    @Test
    void NormalWhenUpJob() throws Exception {
        JobUpVO vo = new JobUpVO();
        vo.setRecruiterId(10018);
        vo.setIfRelease(true);
        JobInfoVO jobInfo = new JobInfoVO();
        jobInfo.setJobName("高考数学辅导");
        jobInfo.setJobType(2);
        jobInfo.setWorkTime(8);
        jobInfo.setStartTime("2023-06-20 08:00:00");
        jobInfo.setEndTime("2023-08-20 16:00:00");
        jobInfo.setWorkPlace("上海市嘉定区曹安公路4800号");
        jobInfo.setSalary(new BigDecimal("2000.00"));
        jobInfo.setEmployeeNum(10);
        jobInfo.setWorkDetails("辅导三角函数、圆锥曲线、导数等重点大题");
        vo.setJobInfo(jobInfo);

        mockMvc.perform(post("/job/upJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("发布成功"));
    }

    @InjectMocks
    JobServiceImpl jobService;

    @Mock
    private OrderService orderService;

    @Mock
    private NotificationService notificationService;

    @Test
    @Rollback
    @Transactional
    void refuseJobhuterWhenCloseRecruiter() {
        try{
            int jobId = 35;
            jobService.closeRecruit(jobId);
            ArgumentCaptor<Integer> refuseArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<Integer>> notificationsCaptor = ArgumentCaptor.forClass(List.class);
            verify(orderService).refuseRestJobhunter(refuseArgumentCaptor.capture());
            verify(notificationService).noticeRestJobhunter(notificationsCaptor.capture());
            Integer cap_jobId = refuseArgumentCaptor.getValue();
            assertEquals(jobId,cap_jobId);
            assertEquals(notificationsCaptor.getValue().size(),11);
//            assertTrue(notificationsCaptor.getValue().size() == 11);
        }
        catch (Exception e){
            System.out.println("测试错误信息："+e.getMessage());
        }
    }


    /*
    @InjectMocks UserServiceImpl service;
    @Mock
    private JobhunterMapper jobhunterMapper;

    @Mock
    private RecruiterMapper recruiterMapper;

    @Mock
    private AdministratorMapper administratorMapper;
    @Test
    @Feature("Integration Testing")
    @Rollback(value = true)
    @Transactional
    void IfConsistentWhenRegistJobhunter() throws SelfDesignException {
        registVO.setUserType(UserType.JOBHUNTER);
        Integer id = 10449;

        Jobhunter jobhunter=new Jobhunter();
        jobhunter.setJobhunterId(id);

        service.regist2(registVO,id);
        ArgumentCaptor<Jobhunter> jobhunterArgumentCaptor =
                ArgumentCaptor.forClass(Jobhunter.class);

        verify(jobhunterMapper).registJobhunter(jobhunterArgumentCaptor.capture());
        Jobhunter capturedJonbunter = jobhunterArgumentCaptor.getValue();
        assertEquals(jobhunter, capturedJonbunter);
    }
    */


    @AfterEach
    void tearDown() {

    }
}