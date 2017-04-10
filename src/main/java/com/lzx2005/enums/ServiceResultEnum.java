package com.lzx2005.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
public enum ServiceResultEnum {
    //通用
    SUCCESS(0,"请求成功"),
    FAIL(-1,"请求失败"),
    SERVICE_ERROR(-2,"服务器出错"),
    DB_ERROR(-3,"数据库操作出错"),
    NEED_PARAMS(-4,"缺少必要的参数"),
    NEED_TOKEN(-5,"缺少token参数"),
    PARSE_TOKEN_ERROR(-6,"解析Token出错，请检查token格式"),

    //数据库增删改
    DELETE_SUCCESS(100,"删除成功"),

    //用户相关
    LOGIN_FAILED(1000,"登录失败"),
    WRONG_USERNAME_OR_PASSWORD(1001,"账号或者密码错误"),
    WRONG_PASSWORD(1003,"密码错误"),
    CANT_FIND_USER(1002,"找不到用户"),

    //菜单相关
    DISH_IS_NOT_EXIST(10000,"无法找到菜品"),

    //订单相关
    CANT_FIND_ORDER(11000,"找不到订单"),
    ORDER_OVER(11001,"订单已经结束"),
    DISH_HAS_NO_MORE(11002,"该菜品已经删除完了"),
    ;
    private int code;
    private String msg;

    ServiceResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceResult toServiceResult(){
        return new ServiceResult()
                .setCode(this.code)
                .setMsg(this.msg)
                .build();
    }

    public String toString(){
        return this.toServiceResult().toString();
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
