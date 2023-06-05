package com.jbgz.pancakejob.service.impl;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.common.UserType;
import com.jbgz.pancakejob.service.UserService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.RegistVO;
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
@AfterEach
    void tearDown(){
    registVO = null;
}
}