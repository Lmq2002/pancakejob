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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PancakejobApplication.class})
@Feature("Unit Testing")
class OrderServiceImplTest {

    @Resource
    OrderService orderService;

    @Resource
    AppealService appealService;

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
//    @Rollback
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
    @Rollback
    @Transactional
    void createOrder_test1() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(-1);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            orderService.createOrder(applyJobVO);
            fail("预期输出错误信息：'不存在此兼职信息'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "不存在该兼职信息");
        }
    }

    /*不存在该求职者*/
    @Test
    @Rollback
    @Transactional
    void createOrder_test2() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(27);
        applyJobVO.setJobhunterId(-1);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            orderService.createOrder(applyJobVO);
            fail("预期输出错误信息：'不存在该求职者'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals( "不存在该求职者",e.getMessage());
        }
    }

    /*申请原因为空*/
    @Test
    @Rollback
    @Transactional
    void createOrder_test3() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(6);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason(null);
        try {
            orderService.createOrder(applyJobVO);
            fail("预期输出错误信息：'申请理由为空'.");
        } catch (Exception e) {
            System.out.println("测试错误信息："+e.getMessage());
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "申请理由为空");

        }
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void createOrder_test4() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(27);
        applyJobVO.setJobhunterId(10009);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            assertDoesNotThrow(()->{
                int res = orderService.createOrder(applyJobVO);
                assertTrue(res > 0);
            });
        } catch (Exception e) {
            System.out.println("测试错误信息：" + e.getMessage());
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
    @Rollback
    @Transactional
    public void changeOrderScore_test1() {
        try {
            boolean res = orderService.changeOrderScore(-1, 4, "jobhunter");
            assertFalse(res);
            fail("预期输出错误信息：'订单不存在'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "订单不存在");
        }
    }

    /*评价类型不存在的情况*/
    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test2() {
        try {
            boolean res = orderService.changeOrderScore(28, 4, "jobhunt");
            assertFalse(res);
            fail("预期输出错误信息：'评价类型错误'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评价类型错误");
        }
    }

    /*评价类型不存在的情况*/
    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test3() {
        try {
            boolean res = orderService.changeOrderScore(28, 4, null);
            assertFalse(res);
            fail("预期输出错误信息：'评价类型为空'.");
        } catch (Exception e) {
            System.out.println("测试错误信息："+e.getMessage());
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评价类型为空");
        }
    }


    /*评价分数边界值测试*/
    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test4() {
        try {
            boolean res = orderService.changeOrderScore(28, -1, "jobhunter");
            assertFalse(res);
            fail("预期输出错误信息：'评分分数不在1-5区间'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评分分数不在1-5区间");
        }
    }

    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test5() {
        try {
            boolean res = orderService.changeOrderScore(28, 0, "jobhunter");
            assertFalse(res);
            fail("预期输出错误信息：'评分分数不在1-5区间'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评分分数不在1-5区间");
        }
    }

    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test6() {
        try {
            boolean res = orderService.changeOrderScore(28, 6, "jobhunter");
            assertFalse(res);
            fail("预期输出错误信息：'评分分数不在1-5区间'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评分分数不在1-5区间");
        }
    }

    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test7() {
        try {
            boolean res = orderService.changeOrderScore(28, 8, "jobhunter");
            assertFalse(res);
            fail("预期输出错误信息：'评分分数不在1-5区间'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "评分分数不在1-5区间");
        }
    }

    /*输入参数合法*/
    @Test
    @Rollback
    @Transactional
    void changeOrderScore_test8() {
        try {
            assertDoesNotThrow(()->{
                boolean res = orderService.changeOrderScore(28, 4, "jobhunter");
                assertTrue(res);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            assertTrue(e instanceof SelfDesignException);
//            assertEquals(e.getMessage(),"不存在该兼职信息");
        }
    }


    //--------------------------------------------------------------------------------------------//
    //OrderService acceptOfferOrNot方法
    //--------------------------------------------------------------------------------------------//

//    测试控制台输出的版本
//    @Test
//    @Rollback
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
    @Rollback
    @Transactional
    void acceptOfferOrNot_test1() {
        try {
            boolean res = orderService.acceptOfferOrNot(-1, true);
            assertFalse(res);
            fail("预期输出错误信息：'订单不存在'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "订单不存在");
        }
    }

    @Test
    @Rollback
    @Transactional
    void acceptOfferOrNot_test2() {
        try {
            boolean res = orderService.acceptOfferOrNot(80, false);
            assertFalse(res);
            fail("预期输出错误信息：'订单不存在'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "订单不存在");
        }
    }

    /*订单存在但订单状态不是已通过*/
    @Test
    @Rollback
    @Transactional
    void acceptOfferOrNot_test3() {
        try {
            boolean res = orderService.acceptOfferOrNot(23, true);
            assertFalse(res);
            fail("预期输出错误信息：'此订单非已通过状态'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "此订单非已通过状态");
        }
    }

    @Test
    @Rollback
    @Transactional
    void acceptOfferOrNot_test4() {
        try {
            boolean res = orderService.acceptOfferOrNot(24, false);
            assertFalse(res);
            fail("预期输出错误信息：'此订单非已通过状态'.");
        } catch (Exception e) {
            assertTrue(e instanceof SelfDesignException);
            assertEquals(e.getMessage(), "此订单非已通过状态");
        }
    }


    /*参数均合法*/
    @Test
    @Rollback
    @Transactional
    void acceptOfferOrNot_test5() {
        try {
            assertDoesNotThrow(()->{
                boolean res = orderService.acceptOfferOrNot(519, true);
                assertTrue(res);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional
    void acceptOfferOrNot_test6() {
        try {
            assertDoesNotThrow(()->{
                boolean res = orderService.acceptOfferOrNot(519, false);
                assertTrue(res);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /*集成测试部分-基于功能*/

    @Test
    @Rollback
    @Transactional
    void whiteBoxTest1() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(27);
        applyJobVO.setJobhunterId(10009);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            assertDoesNotThrow(()->{
                int orderId = orderService.createOrder(applyJobVO);
                assertTrue(orderId > 0);
                boolean res = orderService.sendOfferOrNot(orderId, true);
                assertTrue(res);
                res = orderService.acceptOfferOrNot(orderId, true);
                assertTrue(res);
                res = orderService.changeOrderState(orderId, "已完成");
                assertTrue(res);
                res = orderService.changeOrderScore(orderId, 5, "jobhunter") && orderService.changeOrderScore(orderId, 4, "recruiter");
                assertTrue(res);

                AppealOrderVO appealOrderVO = new AppealOrderVO();
                appealOrderVO.setOrderId(orderId);
                appealOrderVO.setAppealType("求职者评价申诉");
                appealOrderVO.setAppealContent("评分不合理");
                res =appealService.createAppeal(appealOrderVO);
                assertTrue(res);
                appealOrderVO.setAppealType("招聘方评价申诉");
                res =appealService.createAppeal(appealOrderVO);
                assertTrue(res);

                res = orderService.changeOrderState(orderId, "支付状态异常");
                assertTrue(res);
                res = orderService.changeOrderState(orderId, "已完成");
                assertTrue(res);

        } catch (Exception e) {
            System.out.println("测试错误信息:" + e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional
    void whiteBoxTest2() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(6);
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            int orderId = orderService.createOrder(applyJobVO);
            assertTrue(orderId > 0);
            boolean res = orderService.cancelOrder(orderId);
            assertTrue(res);
        } catch (Exception e) {
            System.out.println("测试错误信息:" + e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional
    void whiteBoxTest3() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(27);
        applyJobVO.setJobhunterId(10009);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            assertDoesNotThrow(()->{
                int orderId = orderService.createOrder(applyJobVO);
                assertTrue(orderId > 0);
                boolean res = orderService.sendOfferOrNot(orderId,false);
                assertTrue(res);
            });
        } catch (Exception e) {
            System.out.println("测试错误信息:" + e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional
    void whiteBoxTest4() {
        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobId(27);
        applyJobVO.setJobhunterId(10009);
        applyJobVO.setApplyReason("我擅长这项工作");
        try {
            assertDoesNotThrow(()->{
                int orderId = orderService.createOrder(applyJobVO);
                assertTrue(orderId > 0);
                boolean res = orderService.sendOfferOrNot(orderId,true);
                assertTrue(res);
                res = orderService.acceptOfferOrNot(orderId,false);
                assertTrue(res);
            });
        } catch (Exception e) {
            System.out.println("测试错误信息:" + e.getMessage());
        }

    }
}