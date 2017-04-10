package com.lzx2005.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dao.MongoDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.repository.DishRepository;
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
    private MongoDao mongoDao;

    @Autowired
    private DishRepository dishRepository;

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
        JSONObject order = mongoDao.findOrderByOrderId(orderId);
        if(order==null){
            return ServiceResultEnum.CANT_FIND_ORDER.toServiceResult();
        }
        Dish dish = dishRepository.findOne(dishId);
        if(dish==null){
            return ServiceResultEnum.DISH_IS_NOT_EXIST.toServiceResult();
        }

        JSONObject jsonObject = mongoDao.addDishToOrder(orderId, dish);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult removeDish(String orderId, long dishId) {
        JSONObject order = mongoDao.findOrderByOrderId(orderId);
        if(order==null){
            return ServiceResultEnum.CANT_FIND_ORDER.toServiceResult();
        }
        JSONArray dishes = order.getJSONArray("dishes");
        for(int i=0;i<dishes.size();i++){
            JSONObject dish = dishes.getJSONObject(i);
            Long id = dish.getLong("id");
            if(id==dishId){
                //删除当前的菜品
                mongoDao.removeDishFromOrder(orderId,dish);
            }
        }
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult submitOrder(String orderId) {
        JSONObject jsonObject = mongoDao.submitOrder(orderId);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult cookerFinishOrder(String orderId) {
        JSONObject jsonObject = mongoDao.cookFinish(orderId);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult payOrder(String orderId) {
        //todo 支付接口暂时不考虑
        return null;
    }
}
