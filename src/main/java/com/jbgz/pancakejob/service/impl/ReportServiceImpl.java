package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.entity.Report;
import com.jbgz.pancakejob.service.ReportService;
import com.jbgz.pancakejob.mapper.ReportMapper;
import com.jbgz.pancakejob.vo.ReportVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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


}




