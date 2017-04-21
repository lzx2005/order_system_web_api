package com.lzx2005.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dao.MongoRestaurantDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    MongoRestaurantDao mongoRestaurantDao;

    @Override
    public ServiceResult createRestaurant(JSONObject restaurant) {
        mongoRestaurantDao.insertRestaurant(restaurant);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult getAllMyRestaurant(int userId) {
        List<JSONObject> allMyRestaurant = mongoRestaurantDao.getAllMyRestaurant(userId);
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(allMyRestaurant);
    }
}
