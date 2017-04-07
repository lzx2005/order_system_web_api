package com.lzx2005.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dao.MongoDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    MongoDao mongoDao;

    @Override
    public ServiceResult createOrder(int userId) {
        JSONObject order = new JSONObject();
        order.put("userId",userId);
        String orderId = UUID.randomUUID().toString();
        order.put("orderId", orderId);
        order.put("createTime", LocalDateTime.now());
        order.put("orderStatus",1);

        mongoDao.createOrder(order);
        JSONObject jsonObject = mongoDao.findOrderByOrderId(orderId);
        if(jsonObject!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(jsonObject);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult addDish(String orderId, long dishId) {
        return null;
    }

    @Override
    public ServiceResult removeDish(String orderId, long dishId) {
        return null;
    }

    @Override
    public ServiceResult submitOrder(String orderId) {
        return null;
    }

    @Override
    public ServiceResult cookerFinishOrder(String orderId) {
        return null;
    }

    @Override
    public ServiceResult payOrder(String orderId) {
        return null;
    }
}
