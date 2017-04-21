package com.lzx2005.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
public class Point {
    private double lng;
    private double lat;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
