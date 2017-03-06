package com.lzx2005.dto;

import com.alibaba.fastjson.JSON;

/**
 * Created by Lizhengxian on 2017/3/6.
 */
public class ApiRestResult<T> {

    private int code;
    private String msg;
    private T data;


    public int getCode() {
        return code;
    }

    public ApiRestResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ApiRestResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiRestResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ApiRestResult<T> build(){
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
