package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.ReportDTO;
import com.jbgz.pancakejob.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.vo.ReportDealVO;
import com.jbgz.pancakejob.vo.ReportVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【report】的数据库操作Service
* @createDate 2022-12-30 22:40:08
*/
public interface ReportService extends IService<Report> {
    boolean createReport(ReportVO reportVO);
    List<ReportDTO> getReportList();
    boolean dealReport(ReportDealVO reportDealVO);

}
