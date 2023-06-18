package com.jbgz.pancakejob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jbgz.pancakejob.dto.AppealDTO;
import com.jbgz.pancakejob.entity.Appeal;
import com.jbgz.pancakejob.entity.Order;
import com.jbgz.pancakejob.mapper.AppealMapper;
import com.jbgz.pancakejob.mapper.OrderMapper;
import com.jbgz.pancakejob.mapper.UserMapper;
import com.jbgz.pancakejob.service.AppealService;
import com.jbgz.pancakejob.utils.DateTimeTrans;
import com.jbgz.pancakejob.utils.SelfDesignException;
import com.jbgz.pancakejob.vo.AppealDealVO;
import com.jbgz.pancakejob.vo.AppealOrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CSY0214
 * @description 针对表【appeal】的数据库操作Service实现
 * @createDate 2022-12-30 22:38:20
 */
@Service
public class AppealServiceImpl extends ServiceImpl<AppealMapper, Appeal>
        implements AppealService {
    @Resource
    private AppealMapper appealMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    //转换单个申诉的DTO
    AppealDTO getAppealDTO(Appeal appeal) {
        AppealDTO appealDTO = new AppealDTO();
        appealDTO.setOrderId(appeal.getOrderId());
        appealDTO.setAppealType(appeal.getAppealType());
        appealDTO.setAppealContent(appeal.getAppealContent());
        appealDTO.setAppealTime(DateTimeTrans.datetimeToString(appeal.getAppealTime()));
        appealDTO.setAppealResult(appeal.getAppealResult());
        appealDTO.setStatus(appeal.getStatus());
        return appealDTO;
    }

    //转换申诉列表的DTO
    List<AppealDTO> getAppealDTOList(int userId, List<Appeal> appealList) {
        List<AppealDTO> appealDTOList = new ArrayList<>();
        if (userId == -1)
            for (Appeal appeal : appealList) {
                AppealDTO appealDTO = getAppealDTO(appeal);
                appealDTOList.add(appealDTO);
            }
        else
            for (Appeal appeal : appealList) {
                Order order = orderMapper.selectById(appeal.getOrderId());
                if (order.getJobhunterId() == userId) {
                    AppealDTO appealDTO = getAppealDTO(appeal);
                    appealDTOList.add(appealDTO);
                }
            }
        return appealDTOList;
    }

    //转换

    //创建申诉
    public boolean createAppeal(AppealOrderVO appealOrderVO) throws SelfDesignException {
        if (orderMapper.selectById(appealOrderVO.getOrderId()) == null)
            throw new SelfDesignException("订单不存在");
        else if (appealOrderVO.getAppealType() == null)
            throw new SelfDesignException("申诉类型为空");
        else if (appealOrderVO.getAppealContent() == null)
            throw new SelfDesignException("申诉理由为空");

        QueryWrapper<Appeal> appealWrapper = new QueryWrapper<>();
        appealWrapper.eq("order_id", appealOrderVO.getOrderId())
                .eq("appeal_type", appealOrderVO.getAppealType())
                .eq("status","未审核");
//                .orderByDesc("appeal_time");
//        List<Appeal> appealList = appealMapper.selectList(appealWrapper);
//        if(appealList.size() > 0)
//            if(appealList.get(0).getStatus().equals("未审核"))
//                throw new SelfDesignException("对此订单提出的当前类型的申诉还未处理");
        if(appealMapper.selectOne(appealWrapper) != null)
            throw new SelfDesignException("对此订单提出的当前类型的申诉还未处理");
        else{
            Appeal appeal = new Appeal();
            appeal.setOrderId(appealOrderVO.getOrderId());
            appeal.setAppealType(appealOrderVO.getAppealType());
            appeal.setAppealContent(appealOrderVO.getAppealContent());
            appeal.setAppealTime(new Date());
            appeal.setStatus("未审核");
            return appealMapper.insert(appeal) > 0;
        }
    }

    //获取待处理的申诉列表userId=-1
    //获取某用户的申诉列表userId!=-1(暂无此api)
    public List<AppealDTO> getAppealList(int userId) throws SelfDesignException {
        if (userId != -1 && userMapper.selectById(userId) == null)
            throw new SelfDesignException("该用户不存在");
        QueryWrapper<Appeal> appealWrapper = new QueryWrapper<Appeal>();
        appealWrapper.orderByDesc("appeal_time");
        List<AppealDTO> appealDTOList = getAppealDTOList(userId, appealMapper.selectList(appealWrapper));
        return appealDTOList;
    }

    //获取单个申诉详细信息
//    public AppealDTO getAppealInfo(int orderId,String appealType){
//        AppealDTO appealDTO;
//        return appealDTO;
//    }
    //保存申诉处理结果
    public boolean saveDealResult(AppealDealVO appealDealVO) throws SelfDesignException {
        if(appealDealVO.getAppealResult() == null)
            throw new SelfDesignException("申诉处理结果为空");

        QueryWrapper<Appeal> appealWrapper = new QueryWrapper<>();
        appealWrapper.eq("order_id", appealDealVO.getOrderId())
                .eq("appeal_type", appealDealVO.getAppealType())
                .eq("status","未审核");
//                .orderByDesc("appeal_time");
//        Appeal appeal = new Appeal();
        int re;
        List<Appeal> appealList = appealMapper.selectList(appealWrapper);
        if(appealList.size() == 0)
            throw new SelfDesignException("无相关申诉或申诉已处理");
        else{
            Appeal appeal = appealList.get(0);
            appeal.setAppealResult(appealDealVO.getAppealResult());
            if (appealDealVO.isStatus())
                appeal.setStatus("已通过");
            else
                appeal.setStatus("未通过");
//            appealWrapper.eq("order_id", appealDealVO.getOrderId())
//                    .eq("appeal_type", appealDealVO.getAppealType())
//                    .eq("appeal_time", appeal.getAppealTime());
            re = appealMapper.update(appeal, appealWrapper);
        }
        return re > 0;
    }


}




