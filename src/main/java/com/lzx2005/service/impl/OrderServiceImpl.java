package com.lzx2005.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dao.MongoOrderDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.entity.Order;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.repository.DishRepository;
import com.lzx2005.service.OrderService;
import com.lzx2005.tools.SUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private MongoOrderDao mongoOrderDao;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public ServiceResult createOrder(Order order) {
        Order orders = mongoOrderDao.findActivityOrderByUserId(order.getUserId());
        if(orders!=null){
            return ServiceResultEnum.HAS_ACTIVE_ORDER.toServiceResult();
        }
        mongoOrderDao.createOrder(order);
        //Order orderSaved = mongoOrderDao.findOrderByOrderId(order.getOrderId());
        //if(orderSaved==null){
        //    return ServiceResultEnum.CREATE_ORDER_FAILED.toServiceResult();
        //}
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult findActivityOrder(int userId) {
        Order order = mongoOrderDao.findActivityOrderByUserId(userId);
        if(order!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(order);
        }else{
            return ServiceResultEnum.CANT_FIND_ACTIVE_ORDER.toServiceResult();
        }
    }


    @Override
    public ServiceResult payOrder(String orderId) {
        //todo 支付接口暂时不考虑
        return null;
    }

}
