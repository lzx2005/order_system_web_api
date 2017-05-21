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
        mongoOrderDao.createOrder(order);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult addDish(String orderId, long dishId) {
        JSONObject order = mongoOrderDao.findOrderByOrderId(orderId);
        if(order==null){
            return ServiceResultEnum.CANT_FIND_ORDER.toServiceResult();
        }
        Dish dish = dishRepository.findOne(dishId);
        if(dish==null){
            return ServiceResultEnum.DISH_IS_NOT_EXIST.toServiceResult();
        }

        JSONObject jsonObject = mongoOrderDao.addDishToOrder(orderId, dish);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult removeDish(String orderId, long dishId) {
        JSONObject order = mongoOrderDao.findOrderByOrderId(orderId);
        if(order==null){
            return ServiceResultEnum.CANT_FIND_ORDER.toServiceResult();
        }

        Dish dish = dishRepository.findOne(dishId);
        JSONObject jsonObject = mongoOrderDao.removeDishFromOrder(orderId, dish);
        if(jsonObject!=null){
            logger.info(jsonObject.toString());
        }
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult submitOrder(String orderId) {
        JSONObject jsonObject = mongoOrderDao.submitOrder(orderId);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult cookerFinishOrder(String orderId) {
        JSONObject jsonObject = mongoOrderDao.cookFinish(orderId);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult payOrder(String orderId) {
        //todo 支付接口暂时不考虑
        return null;
    }

    @Override
    public ServiceResult findByUserId(int userId) {
        List<JSONObject> activityOrderByUserId = mongoOrderDao.findActivityOrderByUserId(userId);
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(activityOrderByUserId);
    }
}
