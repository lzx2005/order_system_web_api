package com.lzx2005.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.model.geojson.Point;

import java.util.Date;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private List<Double> position;

    private int belong;
    private Date createTime;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JSONObject toJSONObject(){
        return JSON.parseObject(this.toString());
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
