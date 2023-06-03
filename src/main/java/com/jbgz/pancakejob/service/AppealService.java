package com.jbgz.pancakejob.service;

import com.jbgz.pancakejob.dto.AppealDTO;
import com.jbgz.pancakejob.entity.Appeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.AppealDealVO;
import com.jbgz.pancakejob.vo.AppealOrderVO;
import com.jbgz.pancakejob.vo.ReportDealVO;

import java.util.List;

/**
* @author CSY0214
* @description 针对表【appeal】的数据库操作Service
* @createDate 2022-12-30 22:38:20
*/
public interface AppealService extends IService<Appeal> {

    boolean createAppeal(AppealOrderVO appealOrderVO) throws SelfDesignException;
    //List<AppealDTO> getAppealToDeal();
    List<AppealDTO> getAppealList(int userId) throws SelfDesignException;
    //AppealDTO getAppealInfo(int orderId,String appealType);
    boolean saveDealResult(AppealDealVO appealDealVO) throws SelfDesignException;

}
