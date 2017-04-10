package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/4/7.
 * 订单方法
 */
public interface OrderService {

    /**
     * 创建订单
     * */
    ServiceResult createOrder(int userId);

    /**
     * 加菜
     * */
    ServiceResult addDish(String orderId,long dishId);

    /**
     * 减菜
     * */
    ServiceResult removeDish(String orderId,long dishId);

    /**
     * 提交订单，进入厨房
     * */
    ServiceResult submitOrder(String orderId);

    /**
     * 厨师完成菜单
     * */
    ServiceResult cookerFinishOrder(String orderId);

    /**
     * 付款
     * */
    ServiceResult payOrder(String orderId);

    /**
     * 根据用户找到UserId
     * */
    ServiceResult findByUserId(int userId);
}
