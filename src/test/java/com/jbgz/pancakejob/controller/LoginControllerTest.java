package com.jbgz.pancakejob.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.common.UserType;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.service.AdminConlogService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.EmailAccountVO;
import com.jbgz.pancakejob.vo.FindPasswordVO;
import com.jbgz.pancakejob.vo.LoginVO;
import com.jbgz.pancakejob.vo.RegistVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.test.StepVerifier;

import javax.annotation.Resource;


import static cn.hutool.core.lang.Console.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void NullEmailWhenRegist() throws Exception {
        RegistVO vo=new RegistVO();
        vo.setEmail(null);
        vo.setPassword("123456");
        vo.setUserType(UserType.JOBHUNTER );

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("邮箱不能为空"));
    }

    @Test
    @Rollback(value = true)
    void NullPasswordWhenRegist() throws Exception {
        RegistVO vo=new RegistVO();
        vo.setEmail("123@qq.com");
        vo.setPassword(null);
        vo.setUserType(UserType.RECRUITER );

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("密码不能为空"));

    }

    @Test
    @Rollback(value = true)
    void NullUserTypeWhenRegist() throws Exception {

        RegistVO vo=new RegistVO();
        vo.setEmail("123@qq.com");
        vo.setPassword("123456");
        vo.setUserType(null);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("用户类型不能为空"));
    }

    @Test
    @Rollback(value = true)
    @Transactional
    void WrongUserTypeWhenRegist() throws Exception {

        RegistVO vo=new RegistVO();
        vo.setEmail("testCon@qq.com");
        vo.setPassword("123456");
        vo.setUserType("jobhunasd");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("用户类型不合法"));

    }

    @Test
    @Rollback(value = true)
    void NormalRegist() throws Exception {
        RegistVO vo=new RegistVO();
        vo.setEmail("1739983677@qq.com");
        vo.setPassword("888888");
        vo.setUserType(UserType.ADMIN );

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("注册成功"));

    }

    @Test
    @Rollback(value = true)
    void NullEmailWhenCreateAccount() throws Exception {

        EmailAccountVO vo=new EmailAccountVO();
        vo.setEmail(null);

        mockMvc.perform(post("/register/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("邮箱为空"));
    }

    @Test
    @Rollback(value = true)
    void NormalCreateAccount() throws Exception {

        EmailAccountVO vo=new EmailAccountVO();
        vo.setEmail("1739983677@qq.com");

        mockMvc.perform(post("/register/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("SUCCESS"));

    }

    @Test
    @Rollback(value = true)
    void NullEmailWhenFindIfExist() throws Exception {

        EmailAccountVO vo=new EmailAccountVO();
        vo.setEmail(null);

        mockMvc.perform(post("/register/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("邮箱为空"));


    }

    @Test
    @Rollback(value = true)
    void ExistedEmailWhenFindIfExist() throws Exception {

        EmailAccountVO vo=new EmailAccountVO();
        vo.setEmail("2054402@tongji.edu.cn");

        mockMvc.perform(post("/register/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("邮箱已注册"));
    }

    @Test
    @Rollback(value = true)
    void FindNotExist() throws Exception {

        EmailAccountVO vo=new EmailAccountVO();
        vo.setEmail("1739983677@qq.com");

        mockMvc.perform(post("/register/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("邮箱未注册"));
    }

    @Test
    @Rollback(value = true)
    void NUllAccountWhenLogin() throws Exception {

        LoginVO vo=new LoginVO();
        vo.setAccount(null);
        vo.setPassword("123456");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("账号为空"));

    }

    @Test
    @Rollback(value = true)
    void NUllPasswordWhenLogin() throws Exception {

        LoginVO vo=new LoginVO();
        vo.setAccount("jobhunter@qq.com");
        vo.setPassword(null);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("密码为空"));

    }

    @Test
    @Rollback(value = true)
    void UnexistAccountWhenLogin() throws Exception {

        LoginVO vo=new LoginVO();
        vo.setAccount("000@qq.com");
        vo.setPassword("123456");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("登录失败"));
    }

    @Test
    @Rollback(value = true)
    void NullEmailWhenAlterPassword() throws Exception {

        FindPasswordVO vo=new FindPasswordVO();
        vo.setEmail(null);
        vo.setPassword("123456");

        mockMvc.perform(post("/findPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("邮箱为空"));

    }

    @Test
    @Rollback(value = true)
    void UnexistEmailWhenAlterPassword() throws Exception {

        FindPasswordVO vo=new FindPasswordVO();
        vo.setEmail("testCon@tongji.edu.cn");
        vo.setPassword("123456");

        mockMvc.perform(post("/findPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("修改失败"));
    }

    @Test
    @Rollback(value = true)
    void NullPasswordWhenAlterPassword() throws Exception {
        FindPasswordVO vo=new FindPasswordVO();
        vo.setEmail("123@qq.com");
        vo.setPassword(null);

        mockMvc.perform(post("/findPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_400))
                .andExpect(jsonPath("$.message").value("密码为空"));

    }
    @Test
    @Rollback(value = true)
    void NormalAlterPassword() throws Exception {

        FindPasswordVO vo=new FindPasswordVO();
        vo.setEmail("2054402@tongji.edu.cn");
        vo.setPassword("123456");

        mockMvc.perform(post("/findPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("修改成功"));
    }
    @Test
    @Rollback(value = true)
    void NormalLogin() throws Exception {

        LoginVO vo=new LoginVO();
        vo.setAccount("admin@qq.com");
        vo.setPassword("admin");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(jsonPath("$.code").value(Constants.CODE_200))
                .andExpect(jsonPath("$.message").value("登录成功"));

    }
    @AfterEach
    void tearDown() {
    }
}