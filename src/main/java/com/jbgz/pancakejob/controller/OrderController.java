package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.common.NotificationType;
import com.jbgz.pancakejob.dto.OrderAcceptedDTO;
import com.jbgz.pancakejob.dto.OrderAppliedDTO;
import com.jbgz.pancakejob.dto.OrderDTO;
import com.jbgz.pancakejob.service.AppealService;
import com.jbgz.pancakejob.service.NotificationService;
import com.jbgz.pancakejob.service.OrderService;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.AppealOrderVO;
import com.jbgz.pancakejob.vo.ApplyJobVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private AppealService appealService;

    @Resource
    private NotificationService notificationService;

    //获取报名状态
    @GetMapping("/getApplyState")
    public ResultData getApplyState(Integer jobhunterId, Integer jobId) {
        try {
            if(jobhunterId == null)
                throw new SelfDesignException("求职者ID为空");
            if(jobId == null)
                throw new SelfDesignException("兼职ID为空");
            ResultData result = new ResultData();
            result.code = Constants.CODE_200;
            result.message = orderService.getApplyState(jobhunterId, jobId);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //报名兼职
    @PostMapping("/applyForJob")
    public ResultData applyForJob(@RequestBody ApplyJobVO applyJobVO) {
        try {
            if(applyJobVO.getJobhunterId() == null)
                throw new SelfDesignException("求职者ID为空");
            if(applyJobVO.getJobId() == null)
                throw new SelfDesignException("兼职ID为空");
            ResultData result = new ResultData();
            int orderId = orderService.createOrder(applyJobVO);
            if (orderId > -1) {
                result.code = Constants.CODE_200;
                result.message = "报名成功";
                result.data.put("orderId", orderId);
            } else {
                result.code = Constants.CODE_400;
                result.message = "报名失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //取消报名
    @PutMapping("/cancelApplyForJob")
    public ResultData cancelApplyForJob(Integer orderId) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            ResultData result = new ResultData();
            boolean re = orderService.cancelOrder(orderId);

            if (re) {
                result.code = Constants.CODE_200;
                result.message = "取消报名成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "取消报名失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }

    }

    //获取求职者的订单列表
    @GetMapping("/getJobhunterOrderList")
    public ResultData getJobhunterOrderList(Integer jobhunterId) {
        try {
            if(jobhunterId == null)
                throw new SelfDesignException("求职者ID为空");
            ResultData result = new ResultData();
            List<OrderDTO> order_list = orderService.getOrderList(jobhunterId);
            result.data.put("order_list", order_list);
            result.code = Constants.CODE_200;
            result.message = "请求成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //获取某兼职报名者列表
    @GetMapping("/getAppliedList")
    public ResultData getAppliedList(Integer jobId) {
        try {
            if(jobId == null)
                throw new SelfDesignException("兼职ID为空");
            ResultData result = new ResultData();
            List<OrderAppliedDTO> order_list = orderService.getOrderAppliedList(jobId);
            result.data.put("order_list", order_list);
            result.code = Constants.CODE_200;
            result.message = "请求成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //获取某兼职录用者列表
    @GetMapping("/getAcceptedList")
    public ResultData getAcceptedList(Integer jobId) {
        try {
            if(jobId == null)
                throw new SelfDesignException("兼职ID为空");
            ResultData result = new ResultData();
            List<OrderAcceptedDTO> order_list = orderService.getOrderAcceptedList(jobId);
            result.data.put("order_list", order_list);
            result.code = Constants.CODE_200;
            result.message = "请求成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //求职者确认录用结果
    @PutMapping("/confirmPassResult")
    public ResultData confirmPassResult(Integer orderId, String orderState) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            ResultData result = new ResultData();
            boolean accept = orderState.equals("已录用");
            boolean re = orderService.acceptOfferOrNot(orderId, accept);
            /*向招聘方发送通知*/
            if (re) {
                if (accept)
                    re = notificationService.addNotification(orderId, NotificationType.ACCEPT);
                else
                    re = notificationService.addNotification(orderId, NotificationType.GIVEUP);
            }

            if (re) {
                result.code = Constants.CODE_200;
                result.message = "修改成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "修改失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //招聘方确认是否通过
    @PutMapping("/giveOffer")
    public ResultData giveOffer(Integer orderId, String orderState) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            ResultData result = new ResultData();
            boolean send = orderState.equals("已通过");
            boolean re = orderService.sendOfferOrNot(orderId, send);
            /*NotificationService向求职者发送通知*/
            if (re) {
                if (send)
                    re = notificationService.addNotification(orderId, NotificationType.PASS);
                else
                    re = notificationService.addNotification(orderId, NotificationType.REFUSE);
            }
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "修改成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "修改失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //确认求职者完成工作
    @PutMapping("/confirmJobFinish")
    public ResultData confirmJobFinish(Integer orderId) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            ResultData result = new ResultData();
            boolean re = orderService.finishOrder(orderId);
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "确认成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "确认失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //评价求职者
    @PostMapping("/commentOnJobhunter")
    public ResultData commentOnJobhunter(Integer orderId, Integer jobhunterScore) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            if(jobhunterScore == null)
                throw new SelfDesignException("评分为空");
            ResultData result = new ResultData();
            boolean re = orderService.changeOrderScore(orderId, jobhunterScore, "jobhunter");
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "评分成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "评分失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //评价招聘方
    @PostMapping("/commentOnRecruiter")
    public ResultData commentOnRecruiter(Integer orderId, Integer recruiterScore) {
        try {
            if(orderId == null)
                throw new SelfDesignException("订单ID为空");
            if(recruiterScore == null)
                throw new SelfDesignException("评分为空");
            ResultData result = new ResultData();
            boolean re = orderService.changeOrderScore(orderId, recruiterScore, "recruiter");
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "评分成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "评分失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }

    //订单申诉
    @PostMapping("/appealOrder")
    public ResultData appealOrder(@RequestBody AppealOrderVO appealOrderVO) {
        try {
            if(appealOrderVO.getOrderId() == null)
                throw new SelfDesignException("订单ID为空");
            if(appealOrderVO.getAppealType() == null)
                throw new SelfDesignException("申诉类型为空");
            if(appealOrderVO.getAppealContent() == null)
                throw new SelfDesignException("申诉理由为空");
            ResultData result = new ResultData();

            //是否要判断订单是否已有某类型的申诉？

            if (appealOrderVO.getAppealType().equals("支付申诉"))
                //设置订单状态设置为“支付异常”
                orderService.changeOrderState(appealOrderVO.getOrderId(), "支付异常");

            boolean re = appealService.createAppeal(appealOrderVO);
            //“求职者评价申诉”、“招聘方评价申诉”、“支付申诉”
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "申诉成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "申诉失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error(Constants.CODE_400, e.getMessage());
        }
    }
}
