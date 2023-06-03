package com.jbgz.pancakejob.service.impl;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class})
class OrderServiceImplTest {

    @Resource
    OrderService orderService;

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach，测试开始");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach，测试结束");
    }

    //@Rule
    //public ExpectedException exception =ExpectedException.none();

//    测试控制台输出的代码版本
//    @Test
//    @Transactional
//    void createOrder_test() {
//        ApplyJobVO applyJobVO = new ApplyJobVO();
//        applyJobVO.setJobId(-1);
//        applyJobVO.setJobhunterId(1);
//        applyJobVO.setApplyReason("我擅长这项工作");
//        //记录“标准”输出流（控制台输出）
//        PrintStream console = System.out;
//        final ByteArrayOutputStream outString = new ByteArrayOutputStream();
//        try{ //重新分配“标准”输出流
//            System.setOut(new PrintStream(outString));
//            int res = orderService.createOrder(applyJobVO);
//            assertEquals(-1,res);
//            //判断控制台输出是否正确
//            int end = outString.toString().lastIndexOf("\n");
//            int start = outString.toString().lastIndexOf("\n",end-1);
//            assertEquals("不存在该求职者账号",outString.toString().substring(start+1,end-1));
//        } finally { //还原“标准”输出流
//            System.setOut(console);
//        }
//    }

    //--------------------------------------------------------------------------------------------//
    //OrderService createOrderTest方法
    //--------------------------------------------------------------------------------------------//


    /*不存在该兼职信息*/
    @Test
    @Transactional
    void createOrder_test1(){
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(0);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("哈哈");
        try{
            orderService.createOrder(applyJobVO);
            fail("Should throw a SelfDesignException with message '不存在此兼职信息'.");
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    /*不存在该求职者*/
    @Test
    @Transactional
    void createOrder_test2() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(0);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("哈哈");
        try{
            orderService.createOrder(applyJobVO);
            fail("Should throw a SelfDesignException with message '不存在此兼职信息'.");
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    /*申请原因为空*/
    @Test
    @Transactional
    void createOrder_test3() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(0);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("哈哈");
        try{
            orderService.createOrder(applyJobVO);
            fail("Should throw a SelfDesignException with message '不存在此兼职信息'.");
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    /*参数合法*/
    @Test
    @Transactional
    void createOrder_test4() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(0);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("哈哈");
        try{
            orderService.createOrder(applyJobVO);
            fail("Should throw a SelfDesignException with message '不存在此兼职信息'.");
        }catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    //--------------------------------------------------------------------------------------------//
    //OrderService changeOrderScore方法
    //--------------------------------------------------------------------------------------------//

//    测试控制台输出的版本
//    @Test
//    @Transactional
//    void changeOrderScore_test() {
//        //记录“标准”输出流（控制台输出）
//        PrintStream console = System.out;
//        final ByteArrayOutputStream outString = new ByteArrayOutputStream();
//        try{ //重新分配“标准”输出流
//            System.setOut(new PrintStream(outString));
//            boolean res = orderService.changeOrderScore(1,2,null);
//            assertFalse(res);
//            //判断控制台输出是否正确
//            int end = outString.toString().lastIndexOf("\n");
//            int start = outString.toString().lastIndexOf("\n",end-1);
//            assertEquals("评分类型为空",outString.toString().substring(start+1,end-1));
//        } finally { //还原“标准”输出流
//            System.setOut(console);
//        }
//    }

    /*订单不存在的情况*/
    @Test
    @Transactional
    void changeOrderScore_test1() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    /*评价类型不存在的情况*/
    @Test
    @Transactional
    void changeOrderScore_test2() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    /*评价分数边界值测试*/
    @Test
    @Transactional
    void changeOrderScore_test3() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void changeOrderScore_test4() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void changeOrderScore_test5() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void changeOrderScore_test6() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void changeOrderScore_test7() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    /*输入参数合法*/
    @Test
    @Transactional
    void changeOrderScore_test8() {
        try{
            boolean res = orderService.changeOrderScore(1,4,"jobhunt");
            assertFalse(res);
            fail("");
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    //--------------------------------------------------------------------------------------------//
    //OrderService acceptOfferOrNot方法
    //--------------------------------------------------------------------------------------------//

//    测试控制台输出的版本
//    @Test
//    @Transactional
//    void acceptOfferOrNot_test() {
//        //记录“标准”输出流（控制台输出）
//        PrintStream console = System.out;
//        final ByteArrayOutputStream outString = new ByteArrayOutputStream();
//        try{ //重新分配“标准”输出流
//            System.setOut(new PrintStream(outString));
//            boolean res = orderService.acceptOfferOrNot(-1,true);
//            assertFalse(res);
//            //判断控制台输出是否正确
//            int end = outString.toString().lastIndexOf("\n");
//            int start = outString.toString().lastIndexOf("\n",end-1);
//            assertEquals("订单不存在",outString.toString().substring(start+1,end-1));
//        } finally { //还原“标准”输出流
//            System.setOut(console);
//        }
//    }

    /*订单不存在的情况*/
    @Test
    @Transactional
    void acceptOfferOrNot_test1() {
        try{
            boolean res = orderService.acceptOfferOrNot(-1,true);
            assertFalse(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void acceptOfferOrNot_test2() {
        try{
            boolean res = orderService.acceptOfferOrNot(80,true);
            assertFalse(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void acceptOfferOrNot_test3() {
        try{
            boolean res = orderService.acceptOfferOrNot(100,false);
            assertFalse(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    /*订单存在但订单状态不是已通过*/
    @Test
    @Transactional
    void acceptOfferOrNot_test4() {
        try{
            boolean res = orderService.acceptOfferOrNot(23,true);
            assertFalse(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"订单状态不是已通过");
        }
    }

    @Test
    @Transactional
    void acceptOfferOrNot_test5() {
        try{
            boolean res = orderService.acceptOfferOrNot(24,false);
            assertFalse(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"订单状态不是已通过");
        }
    }


    /*参数均合法*/
    @Test
    @Transactional
    void acceptOfferOrNot_test6() {
        try{
            boolean res = orderService.acceptOfferOrNot(25,true);
            assertTrue(res);
        } catch (Exception e){
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }

    @Test
    @Transactional
    void acceptOfferOrNot_test7() {
        try {
            boolean res = orderService.acceptOfferOrNot(26, true);
            assertTrue(res);
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "不存在该兼职信息");
        }
    }
}