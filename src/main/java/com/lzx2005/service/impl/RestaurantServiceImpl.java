package com.lzx2005.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lzx2005.dao.MongoRestaurantDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Restaurant;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.RestaurantService;
import com.lzx2005.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
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
    public ServiceResult createRestaurant(Restaurant restaurant) {
        mongoRestaurantDao.insertRestaurant(restaurant);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult deleteRestaurant(String restaurantId,int userId) {
        Restaurant restaurant = mongoRestaurantDao.findByRestaurantIdAndUserId(restaurantId, userId);
        if(restaurant==null){
            return ServiceResultEnum.CANT_FIND_USER_RESTAURANT.toServiceResult();
        }
        int n = mongoRestaurantDao.deleteRestaurant(restaurantId);
        if(n>0){
            return ServiceResultEnum.SUCCESS.toServiceResult().setMsg("删除餐厅"+restaurantId+"成功，删除了"+n+"条记录");
        }else{
            return ServiceResultEnum.DELETE_RESTAURANT_FAILED.toServiceResult();
        }
    }

    @Override
    public ServiceResult getAllMyRestaurant(int page,int userId) {
        PageInfo<Restaurant> allMyRestaurant = mongoRestaurantDao.getAllMyRestaurantByPage(userId,page);
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(allMyRestaurant);
    }

    @Override
    public ServiceResult getAllMyRestaurant(int userId) {
        List<Restaurant> allMyRestaurant = mongoRestaurantDao.getAllMyRestaurant(userId);
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(allMyRestaurant);
    }

    @Override
    public ServiceResult getNearRestaurant(double lng, double lat, double length) {
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(mongoRestaurantDao.getNearRestaurant(lng, lat, length));
    }

    @Override
    public ServiceResult findByRestaurentId(String restaurantId) {
        if(StringTools.isEmpty(restaurantId)){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult();
        }
        Restaurant restaurant = mongoRestaurantDao.findByRestaurantId(restaurantId);
        if(restaurant==null){
            return ServiceResultEnum.CANT_FIND_RESTAURANT.toServiceResult();
        }
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(restaurant);
    }
}
