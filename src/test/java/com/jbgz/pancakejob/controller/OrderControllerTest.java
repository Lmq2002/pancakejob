package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.PancakejobApplication;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.AppealOrderVO;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PancakejobApplication.class},
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = {PancakejobApplication.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
//    private RequestBuilder request = null;
//    private RequestBuilder request = null;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Resource
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach，测试开始");
//        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        request = null;
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach，测试结束");
    }

    //----------------------------------------------------------
    //获取兼职报名状态
    //----------------------------------------------------------

    /*求职者ID为空*/
    @Test
    @Rollback
    @Transactional
    void getApplyState_test1() throws Exception{
        String url = "/order/getApplyState";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","")
                                .param("jobId","7")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("求职者ID为空",result.message);
    }

    /*兼职ID为空*/
    @Test
    @Rollback
    @Transactional
    void getApplyState_test2() throws Exception{
        String url = "/order/getApplyState";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10014")
                                .param("jobId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("兼职ID为空",result.message);
    }

    /*求职者不存在*/
    @Test
    @Rollback
    @Transactional
    void getApplyState_test3() throws Exception{
        String url = "/order/getApplyState";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10100")
                                .param("jobId","6")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该求职者",result.message);
    }

    /*兼职信息不存在*/
    @Test
    @Rollback
    @Transactional
    void getApplyState_test4() throws Exception{
        String url = "/order/getApplyState";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10014")
                                .param("jobId","100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该兼职信息",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void getApplyState_test5() throws Exception{
        String url = "/order/getApplyState";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10028")
                                .param("jobId","3")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("已取消",result.message);
    }

    //----------------------------------------------------------
    //报名兼职
    //----------------------------------------------------------

    /*求职者ID为空*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test1() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(null);
        applyJobVO.setJobId(7);
        applyJobVO.setApplyReason("我很细心且认真负责");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("求职者ID为空",result.message);
    }

    /*兼职ID为空*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test2() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setJobId(null);
        applyJobVO.setApplyReason("我很细心且认真负责");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("兼职ID为空",result.message);
    }

    /*申请理由为空*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test3() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(10009);
        applyJobVO.setJobId(27);
        applyJobVO.setApplyReason(null);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("申请理由为空",result.message);
    }

    /*求职者不存在*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test4() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(10100);
        applyJobVO.setJobId(27);
        applyJobVO.setApplyReason("我很细心且认真负责");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该求职者",result.message);
    }

    /*兼职信息不存在*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test5() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(10014);
        applyJobVO.setJobId(100);
        applyJobVO.setApplyReason("我很细心且认真负责");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该兼职信息",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void applyForJob_test6() throws Exception {
        String url = "/order/applyForJob";

        ApplyJobVO applyJobVO = new ApplyJobVO();
        applyJobVO.setJobhunterId(10015);
        applyJobVO.setJobId(38);
        applyJobVO.setApplyReason("我很细心且认真负责");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(applyJobVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("报名成功",result.message);
    }

    //----------------------------------------------------------
    //取消报名
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void cancelApplyForJob_test1() throws Exception {
        String url = "/order/cancelApplyForJob";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*订单ID不存在*/
    @Test
    @Rollback
    @Transactional
    void cancelApplyForJob_test2() throws Exception {
        String url = "/order/cancelApplyForJob";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void cancelApplyForJob_test3() throws Exception {
        String url = "/order/cancelApplyForJob";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","129")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("取消报名成功",result.message);
    }

    //----------------------------------------------------------
    //获取求职者订单列表
    //----------------------------------------------------------

    /*求职者ID为空*/
    @Test
    @Rollback
    @Transactional
    void getJobhunterOrderList_test1() throws Exception {
        String url = "/order/getJobhunterOrderList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("求职者ID为空",result.message);
    }

    /*求职者ID不存在*/
    @Test
    @Rollback
    @Transactional
    void getJobhunterOrderList_test2() throws Exception {
        String url = "/order/getJobhunterOrderList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该求职者",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void getJobhunterOrderList_test3() throws Exception {
        String url = "/order/getJobhunterOrderList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobhunterId","10009")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("请求成功",result.message);
        assertTrue(result.data.containsKey("order_list"));
    }

    //----------------------------------------------------------
    //获取某兼职报名者列表
    //----------------------------------------------------------

    /*兼职ID为空*/
    @Test
    @Rollback
    @Transactional
    void getAppliedList_test1() throws Exception {
        String url = "/order/getAppliedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("兼职ID为空",result.message);
    }

    /*兼职ID不存在*/
    @Test
    @Rollback
    @Transactional
    void getAppliedList_test2() throws Exception {
        String url = "/order/getAppliedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该兼职信息",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void getAppliedList_test3() throws Exception {
        String url = "/order/getAppliedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","3")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
//                        .andExpect(jsonPath("$.code").value(Constants.CODE_200))
//                        .andExpect(jsonPath("$.message").value("请求成功"))
//                        .andExpect(jsonPath("$.data.order_list").exists())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("请求成功",result.message);
        assertTrue(result.data.containsKey("order_list"));
    }


    //----------------------------------------------------------
    //获取兼职员工（录用者）列表
    //----------------------------------------------------------

    /*兼职ID为空*/
    @Test
    @Rollback
    @Transactional
    void getAcceptedList_test1() throws Exception {
        String url = "/order/getAcceptedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("兼职ID为空",result.message);
    }

    /*兼职信息不存在*/
    @Test
    @Rollback
    @Transactional
    void getAcceptedList_test2() throws Exception {
        String url = "/order/getAcceptedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("不存在该兼职信息",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void getAcceptedList_test3() throws Exception {
        String url = "/order/getAcceptedList";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                                .param("jobId","27")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("请求成功",result.message);
        assertTrue(result.data.containsKey("order_list"));
    }

    //----------------------------------------------------------
    //求职者确认是否接受录用
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test1() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","")
                                .param("orderState","已录用")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*接受结果为空*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test2() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","213")
//                                .param("orderState",null)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("接受结果为空",result.message);
    }

    /*接受结果不合理*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test3() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","213")
                                .param("orderState","随意")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("接受结果不合理",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test4() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","100")
                                .param("orderState","已录用")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*接受录用*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test5() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","213")
                                .param("orderState","已录用")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("修改成功",result.message);
    }

    /*放弃录用*/
    @Test
    @Rollback
    @Transactional
    void confirmPassResult_test6() throws Exception {
        String url = "/order/confirmPassResult";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","213")
                                .param("orderState","已放弃")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("修改成功",result.message);
    }

    //----------------------------------------------------------
    //招聘方确认是否录用（是否发放offer）
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test1() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","")
                                .param("orderState","已通过")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*通过结果为空或不合理*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test2() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","129")
//                                .param("orderState",null)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("通过结果为空",result.message);
    }

    /*通过结果不合理*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test3() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","129")
                                .param("orderState","随意")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("通过结果不合理",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test4() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","100")
                                .param("orderState","已通过")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*招聘方通过录用（发放offer）*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test5() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","129")
                                .param("orderState","已通过")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("修改成功",result.message);
    }

    /*招聘方不通过录用（不发放offer）*/
    @Test
    @Rollback
    @Transactional
    void giveOffer_test6() throws Exception {
        String url = "/order/giveOffer";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","129")
                                .param("orderState","未通过")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("修改成功",result.message);
    }


    //----------------------------------------------------------
    //招聘方确认求职者完成工作
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void confirmJobFinish_test1() throws Exception {
        String url = "/order/confirmJobFinish";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void confirmJobFinish_test2() throws Exception {
        String url = "/order/confirmJobFinish";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","100")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void confirmJobFinish_test3() throws Exception {
        String url = "/order/confirmJobFinish";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(url)
                                .param("orderId","50")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("确认成功",result.message);
    }

    //----------------------------------------------------------
    //给求职者评分
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test1() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","")
                                .param("jobhunterScore","4")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test2() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","100")
                                .param("jobhunterScore","4")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*订分为空*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test3() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","129")
                                .param("jobhunterScore","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分为空",result.message);
    }

    /*订分超出1-5范围*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test4() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("jobhunterScore","0")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分分数不在1-5区间",result.message);
    }

    /*订分超出1-5范围*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test5() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("jobhunterScore","6")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分分数不在1-5区间",result.message);
    }

    /*订分超出1-5范围-边界值*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test6() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("jobhunterScore","1")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("评分成功",result.message);
    }

    /*订分超出1-5范围-边界值*/
    @Test
    @Rollback
    @Transactional
    void commentOnJobhunter_test7() throws Exception {
        String url = "/order/commentOnJobhunter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("jobhunterScore","5")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("评分成功",result.message);
    }

    //----------------------------------------------------------
    //给招聘方评分
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test1() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","")
                                .param("recruiterScore","4")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test2() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","100")
                                .param("recruiterScore","4")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*订分为空*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test3() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","129")
                                .param("recruiterScore","")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分为空",result.message);
    }

    /*订分超出1-5范围*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test4() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("recruiterScore","0")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分分数不在1-5区间",result.message);
    }

    /*订分超出1-5范围*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test5() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("recruiterScore","6")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("评分分数不在1-5区间",result.message);
    }

    /*订分超出1-5范围-边界值*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test6() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("recruiterScore","1")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("评分成功",result.message);
    }

    /*订分超出1-5范围-边界值*/
    @Test
    @Rollback
    @Transactional
    void commentOnRecruiter_test7() throws Exception {
        String url = "/order/commentOnRecruiter";
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .param("orderId","127")
                                .param("recruiterScore","5")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(200,result.code);
        assertEquals("评分成功",result.message);
    }

    //----------------------------------------------------------
    //订单申诉
    //----------------------------------------------------------

    /*订单ID为空*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test1() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(null);
        appealOrderVO.setAppealType("求职者评价申诉");
        appealOrderVO.setAppealContent("评分与事实不符");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单ID为空",result.message);
    }

    /*申诉理由为空*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test2() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(28);
        appealOrderVO.setAppealType("求职者评价申诉");
        appealOrderVO.setAppealContent(null);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("申诉理由为空",result.message);
    }

    /*申诉类型为空*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test3() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(28);
        appealOrderVO.setAppealType(null);
        appealOrderVO.setAppealContent("评分与事实不符");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("申诉类型为空",result.message);
    }

    /*申诉类型非法*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test4() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(28);
        appealOrderVO.setAppealType("其他评价申诉");
        appealOrderVO.setAppealContent("评分与事实不符");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("申诉类型不合理",result.message);
    }

    /*订单不存在*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test5() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(100);
        appealOrderVO.setAppealType("求职者评价申诉");
        appealOrderVO.setAppealContent("评分与事实不符");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(400,result.code);
        assertEquals("订单不存在",result.message);
    }

    /*参数合法*/
    @Test
    @Rollback
    @Transactional
    void appealOrder_test6() throws Exception {
        String url = "/order/appealOrder";
        AppealOrderVO appealOrderVO = new AppealOrderVO();

        appealOrderVO.setOrderId(28);
        appealOrderVO.setAppealType("求职者评价申诉");
        appealOrderVO.setAppealContent("评分与事实不符");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appealOrderVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //ObjectMapper objectMapper = new ObjectMapper();
        ResultData result = objectMapper.readValue(response, ResultData.class);
        //System.out.println("返回：" + result);
        assertEquals(Constants.CODE_200,result.code);
        assertEquals("申诉成功",result.message);
    }

}