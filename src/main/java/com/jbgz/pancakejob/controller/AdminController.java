package com.jbgz.pancakejob.controller;

import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.dto.AppealDTO;
import com.jbgz.pancakejob.dto.CompanyAuthenDTO;
import com.jbgz.pancakejob.dto.NoticeDTO;
import com.jbgz.pancakejob.dto.PersonAuthenDTO;
import com.jbgz.pancakejob.service.CompanyAuthenticationService;
import com.jbgz.pancakejob.service.JobTypeService;
import com.jbgz.pancakejob.service.RealnameAuthenticationService;
import com.jbgz.pancakejob.service.*;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.AuditVO;
import com.jbgz.pancakejob.vo.AppealDealVO;
import com.jbgz.pancakejob.vo.NoticeVO;
import com.jbgz.pancakejob.vo.ReportDealVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private JobTypeService jobTypeService;
    @Resource
    private AppealService appealService;
    @Resource
    private ReportService reportService;
    @Resource
    private JobService jobService;
    @Resource
    private OrderService orderService;

    @Resource
    private NoticeService noticeService;

    @Resource
    private RealnameAuthenticationService realnameAuthenticationService;

    @Resource
    private CompanyAuthenticationService companyAuthenticationService;

    //获取所有兼职类型
    @GetMapping("/getJobTypeList")
    public ResultData getJobTypeList() {
        try {
            ResultData result = new ResultData();
            result.data.put("jobtype_list", jobTypeService.getTypeList());
            result.code = 200;
            result.message = "请求成功";
            System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //增加兼职类型
    @PostMapping("/addType")
    public ResultData addType(String typeName) {
        try {
            ResultData result = new ResultData();
            int re = jobTypeService.addJobType(typeName);
            result.code = 200;
            if (re == -1)
                result.message = "兼职类型已存在";
            else if (re == 0)
                result.message = "添加失败";
            else
                result.message = "添加成功";
            System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //删除兼职类型
    @DeleteMapping("/deleteType")
    public ResultData deleteType(int typeId) {
        try {
            ResultData result = new ResultData();
            boolean re = jobTypeService.deleteJobType(typeId);
            result.code = 200;
            if (re)
                result.message = "删除成功";
            else
                result.message = "删除失败";
            System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //修改兼职类型
    @PutMapping("/changeType")
    public ResultData changeType(int typeId, String typeName) {
        try {
            ResultData result = new ResultData();
            int re = jobTypeService.changeJobType(typeId, typeName);
            result.code = 200;
            if (re == -1)
                result.message = "兼职类型已存在";
            else if (re == 0)
                result.message = "修改失败";
            else
                result.message = "修改成功";
            System.out.println("result:" + result);
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //获取所有待审核的兼职列表
    @GetMapping("/getJobUnreviewed")
    public ResultData getJobUnreviewed(int jobId) {
        String state = "未审核";
        if (jobId == -1) {//获取整个兼职列表
            try {
                ResultData result = new ResultData();
                result.data.put("job_list", jobService.getJobList(state));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            } catch (Exception e) {
                System.out.println("错误信息" + e.getMessage());
                return ResultData.error();
            }
        } else {//获取单个兼职信息
            try {
                ResultData result = new ResultData();
                result.data.put("job_list", jobService.getJobInfo(jobId));
                result.message = "请求成功";
                result.code = 200;
                System.out.println(result);//打印观察
                return result;
            } catch (Exception e) {
                System.out.println("错误信息" + e.getMessage());
                return ResultData.error();
            }
        }
    }

    //审核or冻结兼职
    @PostMapping("/auditJobUnreviewed")
    public ResultData auditJobUnreviewed(int jobId, String jobState) {
        try {
            ResultData result = new ResultData();
            if (jobService.changeJobState(jobId, jobState))
                result.message = "审核兼职处理成功";
            else
                result.message = "审核兼职处理失败";
            result.code = Constants.CODE_200;
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //获取订单申诉列表
    @GetMapping("/getAppealList")
    public ResultData getAppealList() {
        try {
            ResultData result = new ResultData();
            List<AppealDTO> appealDTOList = appealService.getAppealList(-1);
            if (appealDTOList != null) {
                result.data.put("appeal_list", appealDTOList);
                result.code = Constants.CODE_200;
                result.message = "成功获取申诉列表";
            } else {
                result.code = Constants.CODE_400;
                result.message = "获取申诉列表失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //审核订单申诉
    @PostMapping("/dealAppeal")
    public ResultData dealAppeal(@RequestBody AppealDealVO appealDealVO) {
        try {
            ResultData result = new ResultData();
            //“求职者评价申诉”、“招聘方评价申诉”、“支付申诉”
            boolean re = appealService.saveDealResult(appealDealVO);
            //申诉通过，将分数置为0
            if (re && appealDealVO.isStatus()) {
                if (appealDealVO.getAppealType().equals("求职者评价申诉")) {
                    //对求职者分数存疑-求职者提出的申诉
                    re = orderService.changeOrderScore(appealDealVO.getOrderId(), 0, "jobhunter");
                } else if (appealDealVO.getAppealType().equals("招聘方评价申诉")) {
                    re = orderService.changeOrderScore(appealDealVO.getOrderId(), 0, "recruiter");
                }
            }
            if (re) {
                result.code = Constants.CODE_200;
                result.message = "审核订单申诉成功";
            } else {
                result.code = Constants.CODE_400;
                result.message = "审核订单申诉失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //获取兼职举报列表
    @GetMapping("/getReportList")
    public ResultData getReportList() {
        try {
            ResultData result = new ResultData();
            result.data.put("report_list", reportService.getReportList());
            result.code = Constants.CODE_200;
            result.message = "获取兼职举报列表成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //审核兼职举报
    @PostMapping("/auditReport")
    public ResultData auditReport(@RequestBody ReportDealVO reportDealVO) {
        try {
            ResultData result = new ResultData();
            if (reportDealVO.isReportState())
                jobService.changeJobState(reportDealVO.getJobId(), "已冻结");
            if (reportService.dealReport(reportDealVO)) {
                result.code = Constants.CODE_200;
                result.message = "兼职举报审核成功";
            } else {
                result.code = Constants.CODE_200;
                result.message = "兼职举报审核失败";
            }
            return result;
        } catch (Exception e) {
            System.out.println("错误信息" + e.getMessage());
            return ResultData.error();
        }
    }

    //获取公告列表
    @GetMapping("/getNoticeList")
    public ResultData getNoticeList(String status) {
        try {
            ResultData result = new ResultData();
            result.data.put("notice_list", noticeService.getNoticeList(status));
            result.code = Constants.CODE_200;
            result.message = "获取公告列表成功";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }

    //添加公告
    @PostMapping("/addNotice")
    public ResultData addNotice(@RequestBody NoticeVO noticeVO) {
        try {
            ResultData result = new ResultData();
            boolean re = noticeService.addNotice(noticeVO);
            result.code = Constants.CODE_200;
            if (re)
                result.message = "添加公告成功";
            else
                result.message = "添加公告失败";
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }

    //发布、撤销公告
    @PutMapping("/releaseOrRepealNotice")
    public ResultData releaseOrRepealNotice(int noticeId, String status) {
        try {
            ResultData result = new ResultData();
            boolean re = noticeService.manageNotice(noticeId, status);
            if (re)
                result.message = "发布/撤销公告成功";
            else
                result.message = "发布/撤销公告失败";
            result.code = Constants.CODE_200;
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }

    //删除公告
    @DeleteMapping("/deleteNotice")
    public ResultData deleteNotice(int noticeId) {
        try {
            ResultData result = new ResultData();
            boolean re = noticeService.deleteNotice(noticeId);
            if (re)
                result.message = "删除公告成功";
            else
                result.message = "删除公告失败";
            result.code = Constants.CODE_200;
            return result;
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            return ResultData.error();
        }
    }

    /**
     * 功能：审查求职者实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/auditUserAuthentication")
    public ResultData auditUserAuthentication(@RequestBody AuditVO vo) {
        try {
            boolean tmp = realnameAuthenticationService.auditAuthentication(vo);
            if (tmp) return new ResultData(Constants.CODE_200, "成功", null);
            return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }


    /**
     * 功能：获取求职者实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @GetMapping("/getUserAuthenticationList")
    public ResultData getUserAuthenticationList(@RequestParam(value = "jobhunterId", required = false) Integer jobhunterId) {
        try {
            List<PersonAuthenDTO> list = realnameAuthenticationService.getAuthenList(jobhunterId);
            if (list != null) {
                ResultData result = new ResultData();
                result.data.put("personauthen_list", list);
                result.code = Constants.CODE_200;
                result.message = "成功";
                return result;
            } else return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }

    }


    /**
     * 功能：审查招聘方企业申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/auditCompanyAuthentication")
    public ResultData auditCompanyAuthentication(@RequestBody AuditVO vo) {
        try {
            boolean tmp = companyAuthenticationService.auditAuthentication(vo);
            if (tmp) return new ResultData(Constants.CODE_200, "成功", null);
            return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取企业实名申请
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @GetMapping("/getCompanyAuthenticationList")
    public ResultData getCompanyAuthenticationList(@RequestParam(value = "recruiterId", required = false) Integer recruiterId) {
        try {
            List<CompanyAuthenDTO> list = companyAuthenticationService.getAuthenList(recruiterId);
            if (list != null) {
                ResultData result = new ResultData();
                result.data.put("companyauthen_list", list);
                result.code = Constants.CODE_200;
                result.message = "成功";
                return result;
            } else return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

}

