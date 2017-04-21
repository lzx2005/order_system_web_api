package com.lzx2005.service;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
public interface RestaurantService {

    ServiceResult createRestaurant(JSONObject restaurant);

    ServiceResult getAllMyRestaurant(int userId);
}
