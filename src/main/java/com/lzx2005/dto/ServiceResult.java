package com.lzx2005.dto;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lizhengxian on 2017/3/3.
 */
public class ServiceResult {
    int code;
    String msg;
    Object data;

    public int getCode() {
        return code;
    }

    public ServiceResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ServiceResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ServiceResult setData(Object data) {
        this.data = data;
        return this;
    }

    public ServiceResult build(){
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
