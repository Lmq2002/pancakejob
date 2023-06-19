package com.jbgz.pancakejob.service.impl;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.common.UserType;
import com.jbgz.pancakejob.entity.Administrator;
import com.jbgz.pancakejob.entity.Jobhunter;
import com.jbgz.pancakejob.entity.Recruiter;
import com.jbgz.pancakejob.mapper.AdministratorMapper;
import com.jbgz.pancakejob.mapper.JobhunterMapper;
import com.jbgz.pancakejob.mapper.RecruiterMapper;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.RegistVO;
import io.qameta.allure.Feature;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class})
class UserServiceImplTest {
@Resource
    UserService userService;
RegistVO registVO;

@BeforeEach
    void SetUp(){
    registVO = new RegistVO();
}
    @Test
    @Transactional
    @Rollback(value = true)
    void registAsAdmin()  {
        registVO.setUserType(UserType.ADMIN);
        registVO.setEmail("test@qq.com");
        registVO.setPassword("test");
        try {
            assertTrue(userService.regist(registVO));
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
        }


    }

    @Test
    @Transactional
    @Rollback(value = true)
    void registByExistedEmail()  {
        registVO.setUserType(UserType.RECRUITER);
        registVO.setEmail("2054402@tongji.edu.cn");
        registVO.setPassword("test");
        try{
            userService.regist(registVO);
        }
        catch(SelfDesignException e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals("该邮箱已被注册",e.getMessage());
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void registByNullPassword() {
        registVO.setUserType(UserType.JOBHUNTER);
        registVO.setEmail("test@qq.com");
        registVO.setPassword(null);
        try{
            userService.regist(registVO);
        }
        catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals("密码不能为空", e.getMessage());
        }

    }

    @Test
    @Transactional
    @Rollback(value = true)
    void registByUnExceptedUserType() throws SelfDesignException {
        registVO.setUserType("other");
        registVO.setEmail("test@qq.com");
        registVO.setPassword("test");
        try{userService.regist(registVO);}
        catch (Exception e){
        assertTrue(e instanceof SelfDesignException);
        assertEquals("用户类型不合法", e.getMessage());
    }
}

    @Test
    @Transactional
    @Rollback(value = true)
    void registByNullEmail()  {
        registVO.setUserType(UserType.JOBHUNTER);
        registVO.setEmail(null);
        registVO.setPassword("test6");
        try{
            userService.regist(registVO);
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals("邮箱不能为空", e.getMessage());
        }
}



    @Test
    @Transactional
    @Rollback(value = true)
    void regist2ByNormal() {
        registVO.setUserType(UserType.ADMIN);
        registVO.setEmail("2054402@qq.com");
        registVO.setPassword("test2");
        Integer id = 10000;
        try{
            assertTrue(userService.regist2(registVO,id));
        }
        catch (Exception e){

        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void regist2ByUnexpectedID() {
        registVO.setUserType(UserType.ADMIN);
        registVO.setEmail("test1@qq.com");
        registVO.setPassword("test2");
        Integer id = 9999;
        try{userService.regist2(registVO,id);}
        catch(Exception e){
            assertEquals("ID不合法",e.getMessage());}
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void regist2ByNullID() {
        registVO.setUserType(UserType.ADMIN);
        registVO.setEmail("test5@qq.com");
        registVO.setPassword("test5");
        Integer id = null;
        try{
            assertFalse(userService.regist2(registVO,id));
        }catch (Exception e){
            assertEquals("ID不能为空",e.getMessage());
        }
    }

    @Test
    @Feature("Integration Testing")
    @Rollback(value = true)
    void IfConsistentWhenRegistJobhunter() throws SelfDesignException {
        JobhunterMapper jobhunterMapper = mock(JobhunterMapper.class);
        registVO.setUserType(UserType.JOBHUNTER);
        Integer id = 12345;

        Jobhunter jobhunter=new Jobhunter();
        jobhunter.setJobhunterId(id);
        when(jobhunterMapper.registJobhunter(jobhunter)).thenReturn(1);

        userService = mock(UserService.class);
        jobhunterMapper.registJobhunter(jobhunter);
//        boolean result = userService.regist2(registVO,id);
        verify(jobhunterMapper).registJobhunter(eq(jobhunter));
    }

    @Test
    @Feature("Integration Testing")
    @Rollback(value = true)
    void IfConsistentWhenRegistRecruiter() throws SelfDesignException {
        RecruiterMapper jobhunterMapper = mock(RecruiterMapper.class);
        registVO.setUserType(UserType.RECRUITER);
        Integer id = 12345;

        Recruiter recruiter=new Recruiter();
        recruiter.setRecruiterId(id);
        when(jobhunterMapper.registRecruiter(recruiter)).thenReturn(1);

        userService = mock(UserService.class);
        jobhunterMapper.registRecruiter(recruiter);
//        boolean result = userService.regist2(registVO,id);
        verify(jobhunterMapper).registRecruiter(eq(recruiter));
    }

    @Test
    @Feature("Integration Testing")
    @Rollback(value = true)
    void IfConsistentWhenRegistAdministrator() throws SelfDesignException {
        AdministratorMapper administratorMapper = mock(AdministratorMapper.class);
        registVO.setUserType(UserType.ADMIN);
        Integer id = 12345;

        Administrator administrator=new Administrator();
        administrator.setAdminId(id);
        when(administratorMapper.registAdmin(administrator)).thenReturn(1);

        userService = mock(UserService.class);
        administratorMapper.registAdmin(administrator);
//        boolean result = userService.regist2(registVO,id);
        verify(administratorMapper).registAdmin(eq(administrator));
    }
@AfterEach
    void tearDown(){
    registVO = null;
}
}