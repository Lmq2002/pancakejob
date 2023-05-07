package com.jbgz.pancakejob.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jbgz.pancakejob.common.Constants;
import com.jbgz.pancakejob.common.JobStatus;
import com.jbgz.pancakejob.common.RealNameStatus;
import com.jbgz.pancakejob.dto.*;
import com.jbgz.pancakejob.entity.CompanyAuthentication;
import com.jbgz.pancakejob.entity.JobType;
import com.jbgz.pancakejob.entity.RealnameAuthentication;
import com.jbgz.pancakejob.entity.User;
import com.jbgz.pancakejob.service.*;
import com.jbgz.pancakejob.utils.ResultData;
import com.jbgz.pancakejob.vo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobhunterService jobhunterService;
    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private RealnameAuthenticationService realnameAuthenticationService;
    @Autowired
    private CompanyAuthenticationService companyAuthenticationService;

    @Autowired
    private FavoritesDirService favoritesDirService;

    @Autowired
    private  CollectJobService collectJobService;

    @Autowired
    private FavoritesDirService dirService;

    /**
     * 功能：获取个人信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/2
     * 修改时间: 2023/5/3
     * 修改内容:传参为空时返回全部user表项
     */
    @GetMapping("/Jobhunter/personInfo/get")
    public ResultData getPersonalInfo(@RequestParam(value = "jobhunterId", required = false) Integer id) {
        try {
            GetPersonalInfoVO vo = new GetPersonalInfoVO();
            vo.setJobhunterId(id);
            List<PersonalInfoDTO> data = jobhunterService.getPersonalInfo(vo);
            ResultData result = new ResultData();
            if (data != null) {
                result.code = Constants.CODE_200;
                result.message = "成功";
                result.data.put("person_list", data);
                return result;
            } else
                return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：设置个人信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/2
     */
    @PutMapping("/Jobhunter/personInfo/set")
    public ResultData setPersonalInfo(@RequestBody PersonalInfoVO vo) {
        try {
            boolean tmp = jobhunterService.setPersonalInfo(vo);
            if (tmp)
                return new ResultData(Constants.CODE_200, "成功", null);
            else
                return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取用户联系方式
     * 状态：正在测试中中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/3
     */
    @GetMapping("/contactMethod/get")
    public ResultData getContactMethod(@RequestParam("userId") Integer userId) {
        try {
            User user = userService.getById(userId);
            if (user != null) {
                ResultData result = new ResultData();
                result.code = Constants.CODE_200;
                result.message = "存在联系方式";
                result.data.put("contactMethod", user.getContactMethod());
                return result;
            } else return new ResultData(Constants.CODE_400, "不存在联系方式", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加用户联系方式
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/2
     * 完成时间：2023/1/3
     */
    @PostMapping("/contactMethod/set")
    public ResultData setContactMethod(@RequestBody ContactMethodVO vo) {
        try {
            boolean tmp = userService.setContactMethod(vo);
            if (tmp)
                return new ResultData(Constants.CODE_200, "成功", null);
            else return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况:" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加求职者实名认证
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     */
    @PutMapping("/Jobhunter/authentication")
    public ResultData addJobhunterAuthentication(@RequestBody GetJobhunterAuthenticationVO vo) {
        try {
            RealnameAuthentication authentication = new RealnameAuthentication();
            authentication.setJobhunterId(vo.getJobhunterId());
            authentication.setIdcard(vo.getIdcard());
            authentication.setIdentification(vo.getIdentification());
            authentication.setRealName(vo.getRealName());
            authentication.setApplyTime(new Date());
            authentication.setCheckStatus(RealNameStatus.WAITING);
            if (realnameAuthenticationService.save(authentication))
                return new ResultData(Constants.CODE_200, "成功", null);
            else return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况:" + e.getMessage());
            return ResultData.sys_error();
        }

    }

    @GetMapping("/Jobhunter/getAuthentication")
    public ResultData getJobhunterAuthentication(@RequestParam("jobhunterId")Integer jobhunterId){
        try{
            List<PersonAuthenDTO> list =realnameAuthenticationService.getAuthen(jobhunterId);
            if(list!=null) {
                ResultData result = new ResultData();
                result.message="成功";
                result.data.put("personauthen_list",list);
                result.code = Constants.CODE_200;
                return result;
            }
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况:" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：通过jobhunterid获取收藏夹列表
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/5/7
     */
    @GetMapping("/jobhunter/favorites")
    public ResultData getFavoritesDirList(@RequestParam("jobhunterId") Integer jobhunterId) {
        try {
            ResultData result = new ResultData();
            result.code = Constants.CODE_200;
            result.message = "成功";
            result.data.put("favorites_list", favoritesDirService.getDirList(jobhunterId));
            return result;
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加收藏
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/jobhunter/addCollect")
    public ResultData addCollect(@RequestBody CollectVO vo){
        try{
            boolean tmp = collectJobService.addCollect(vo);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");
        }
        catch (Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：移动收藏
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PutMapping("/jobhunter/moveCollect")
    public ResultData moveCollect(@RequestBody CollectVO vo){
        try{
            boolean tmp = collectJobService.updatePosition(vo);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");
        }
        catch (Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：删除收藏
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/7
     * 完成时间：2023/5/7
     */
    @DeleteMapping("/jobhunter/cancelCollect")
    public ResultData cancelCollect(
            @RequestParam("jobhunterId") Integer jobhunterId,
            @RequestParam("jobId") Integer jobId){
        try{
            boolean tmp = collectJobService.deleteCollect(jobhunterId,jobId);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");
        }
        catch (Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加收藏夹
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/7
     * 完成时间：2023/5/7
     */
    @PostMapping("/jobhunter/addFavoritesDir")
    public ResultData addDir(
            @RequestParam("jobhunterId") Integer jobhunterId,
            @RequestParam("favoritesDirName") String dirName ){
        try{
            boolean tmp = dirService.addDir(jobhunterId,dirName);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");
        }
        catch(Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }

    }


    /**
     * 功能：重命名收藏夹
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/7
     * 完成时间：2023/5/7
     */
    @PutMapping("/jobhunter/renameFavoritesDir")
    public ResultData renameDir(
            @RequestParam("jobhunterId") Integer jobhunterId,
            @RequestParam("favoritesDirName")String dirName,
            @RequestParam("favoritesDirId")Integer dirId){
        try{
            boolean tmp = dirService.updateDirName(jobhunterId,dirName,dirId);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");

        }
        catch (Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }


    /**
     * 功能：移除收藏夹
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/7
     * 完成时间：2023/5/7
     */
    @DeleteMapping("/jobhunter/removeFavoritesDir")
    public ResultData deleteDir(
            @RequestParam("jobhunterId") Integer jobhunterId,
            @RequestParam("favoritesDirId") Integer dirId){
        try{
            boolean tmp = favoritesDirService.deleteDir(jobhunterId,dirId);
            if(tmp) {
                ResultData result = new ResultData(
                        Constants.CODE_200,
                        "成功",
                        null);
                return result;
            }
            return ResultData.error(Constants.CODE_400,"失败");

        }
        catch (Exception e){
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：添加企业认证
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     */
    @PutMapping("/recruiter/authentication")
    public ResultData addRecruiterAuthentication(@RequestBody GetRecruiterAuthenticationVO vo) {
        try {
            CompanyAuthentication authentication = new CompanyAuthentication();
            authentication.setRecruiterId(vo.getRecruiterId());
            authentication.setCompanyName(vo.getCompanyName());
            authentication.setCompanyId(vo.getCompanyId());
            authentication.setCompanyType(vo.getCompanyType());
            authentication.setCertification(vo.getCertification());
            authentication.setApplyTime(new Date());
            authentication.setCheckStatus(RealNameStatus.WAITING);
            if (companyAuthenticationService.save(authentication))
                return new ResultData(Constants.CODE_200, "成功", null);
            else return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况:" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    @GetMapping("/recruiter/getAuthentication")
    public ResultData getRecruiterAuthentication(@RequestParam("recruiterId")Integer recruiterId){
        try{
            List<CompanyAuthenDTO> list =companyAuthenticationService.getAuthen(recruiterId);
            if(list!=null) {
                ResultData result = new ResultData();
                result.message="成功";
                result.data.put("companyauthen_list",list);
                result.code = Constants.CODE_200;
                return result;
            }
            else return new ResultData(Constants.CODE_400,"失败",null);
        }
        catch (Exception e){
            System.out.println("异常情况:" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取企业信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     */
    @GetMapping("/recruiter/personInfo/get")
    public ResultData getRecruiterPersonInfo(@RequestParam(value = "recruiterId", required = false) Integer recruiterId) {
        try {
            List<RecruiterPersonInfoDTO> dto = recruiterService.getInfo(recruiterId);
            if (dto == null)
                return new ResultData(Constants.CODE_400, "失败", null);
            ResultData result = new ResultData();
            result.code = Constants.CODE_200;
            result.message = "成功";
            result.data.put("company_list", dto);
            return result;
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：设置企业信息
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/1/3
     * 完成时间：2023/1/3
     */
    @PutMapping("/recruiter/personInfo/set")
    public ResultData setRecruiterPersonInfo(@RequestBody RecruiterPersonInfoVO vo) {
        try {
            boolean tmp = recruiterService.setInfo(vo);
            if (tmp)
                return new ResultData(Constants.CODE_200, "成功", null);
            else
                return new ResultData(Constants.CODE_400, "失败", null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：获取兼职草稿列表
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/3
     * 完成时间：2023/5/3
     */
    @GetMapping("/recruiter/getJobDraftList")
    public ResultData getJobDraftList(@RequestParam("recruiterId") Integer recruiterId) {
        try {
            ResultData result = new ResultData();
            result.data.put("job_list", jobService.getJobDraftList(recruiterId));
            result.code = Constants.CODE_200;
            result.message = "成功";
            return result;
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }


    /**
     * 功能：发布招聘方的兼职草稿，改变job状态
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/3
     * 完成时间：2023/5/3
     */
    @PostMapping("/recruiter/upJobDraft")
    public ResultData upJobDraft(@RequestParam("jobId") Integer jobId, @RequestParam("jobState") String jobState) {
        try {
            boolean tmp = jobService.upJobDraft(jobId);
            if (tmp) {
                ResultData result = new ResultData();
                result.message = "上传成功";
                result.code = Constants.CODE_200;
                result.data = null;
                return result;
            } else {
                return ResultData.error(Constants.CODE_400, "上传失败");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：修改兼职草稿
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @PostMapping("/recruiter/changeJobDraft")
    public ResultData changeJobDraft(@RequestBody JobDataVO vo) {
        try {
            System.out.println("!!! " + vo.getJobdata());
            boolean tmp = jobService.changeJobDraft(vo);
            if (tmp) {
                ResultData result = new ResultData();
                result.data = null;
                result.message = "草稿修改成功";
                result.code = Constants.CODE_200;
                return result;
            }
            return ResultData.error(Constants.CODE_400, "失败");
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return ResultData.sys_error();
        }
    }

    /**
     * 功能：删除兼职草稿
     * 状态：正在测试中
     * 负责人：lmq
     * 新建时间：2023/5/6
     * 完成时间：2023/5/6
     */
    @DeleteMapping("/recruiter/deleteJobDraft")
    public ResultData deleteJobDraft(@RequestParam("jobId") Integer id) {
        try {
            boolean tmp = jobService.deleteJob(id, JobStatus.DRAFT);
            if(tmp){
                ResultData result = new ResultData(Constants.CODE_200,"草稿删除成功",null);
                return result;
            }
            return new ResultData(Constants.CODE_400,"草稿删除失败",null);
        } catch (Exception e) {
            System.out.println("异常情况：" + e.getMessage());
            return null;
        }
    }
}