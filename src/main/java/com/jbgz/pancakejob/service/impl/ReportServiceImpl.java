package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.ReportDTO;
import com.jbgz.pancakejob.entity.Report;
import com.jbgz.pancakejob.service.ReportService;
import com.jbgz.pancakejob.mapper.ReportMapper;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.vo.ReportDealVO;
import com.jbgz.pancakejob.vo.ReportVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author CSY0214
* @description 针对表【report】的数据库操作Service实现
* @createDate 2022-12-30 22:40:08
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
    implements ReportService{
    @Resource
    private ReportMapper reportMapper;

    //获取单个兼职举报的DTO
    ReportDTO getReportDTO(Report report){
        ReportDTO reportDTO=new ReportDTO();
        reportDTO.setJobhunterId(report.getJobhunterId());
        reportDTO.setJobId(report.getJobId());
        reportDTO.setReportReason(report.getReportReason());
        reportDTO.setReportState(report.getReportState());
        reportDTO.setReportResult(report.getReportResult());
        reportDTO.setReportTime(DateTimeTrans.datetimeToString(report.getReportTime()));
        return reportDTO;
    }

    //获取兼职举报DTO列表
    List<ReportDTO> getReportDTOList(List<Report> reportList){

        List<ReportDTO> reportDTOList=new ArrayList<>();
        for(Report report:reportList){
            ReportDTO reportDTO=getReportDTO(report);
            reportDTOList.add(reportDTO);
        }
        return reportDTOList;
    }

    //举报兼职
    public boolean createReport(ReportVO reportVO){
        Report report=new Report();
        report.setJobhunterId(reportVO.getJobhunterId());
        report.setJobId(reportVO.getJobId());
        report.setReportReason(reportVO.getReason());
        report.setReportState("未审核");
        report.setReportTime(new Date());

        int re=reportMapper.insert(report);
        System.out.println("insert:"+re);
        return re>0;
    }

    //获取兼职举报列表
    public List<ReportDTO> getReportList(){
        QueryWrapper<Report> reportWrapper=new QueryWrapper<>();
        reportWrapper.orderByDesc("report_time");
        List<ReportDTO> reportDTOList=getReportDTOList(reportMapper.selectList(reportWrapper));
        return reportDTOList;
    }

    //审核兼职举报
    public boolean dealReport(ReportDealVO reportDealVO){
        QueryWrapper<Report> reportWrapper=new QueryWrapper<>();
        reportWrapper.eq("jobhunter_id",reportDealVO.getJobhunterId())
                .eq("job_id",reportDealVO.getJobId());
        Report report=new Report();
        report.setJobhunterId(reportDealVO.getJobhunterId());
        report.setJobId(reportDealVO.getJobId());
        if(reportDealVO.isReportState())
            report.setReportState("已通过");
        else
            report.setReportState("未通过");
        report.setReportResult(reportDealVO.getReportResult());
        return reportMapper.update(report,reportWrapper)>0;
        //return true;
    }


}




