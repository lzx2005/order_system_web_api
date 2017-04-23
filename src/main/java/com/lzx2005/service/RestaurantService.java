package com.lzx2005.service;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Restaurant;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
public interface RestaurantService {

    ServiceResult createRestaurant(Restaurant restaurant);

    ServiceResult getAllMyRestaurant(int page,int userId);

    ServiceResult getAllMyRestaurant(int userId);
}
