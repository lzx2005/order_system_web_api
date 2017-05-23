package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Order;

/**
 * Created by Lizhengxian on 2017/4/7.
 * 订单方法
 */
public interface OrderService {

    /**
     * 创建订单
     * */
    ServiceResult createOrder(Order order);


    /**
     * 查找订单
     * @param userId
     * @return
     */
    ServiceResult findActivityOrder(int userId);

    /**
     * 付款
     * */
    ServiceResult payOrder(String orderId);

}
