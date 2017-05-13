package com.lzx2005.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.model.geojson.Point;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private String tag;
    //评分
    private double score;
    //每月售出数量
    private int soldPerMonth;
    //位置信息
    private List<Double> position;
    //优惠信息
    private HashMap<String,String> preferential;
    //餐厅图标
    private String avatar;
    private int belong;
    private Date createTime;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JSONObject toJSONObject(){
        return JSON.parseObject(this.toString());
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSoldPerMonth() {
        return soldPerMonth;
    }

    public void setSoldPerMonth(int soldPerMonth) {
        this.soldPerMonth = soldPerMonth;
    }

    public HashMap<String, String> getPreferential() {
        return preferential;
    }

    public void setPreferential(HashMap<String, String> preferential) {
        this.preferential = preferential;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
