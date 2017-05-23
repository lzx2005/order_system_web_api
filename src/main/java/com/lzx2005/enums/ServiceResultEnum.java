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
    WRONG_PAGE_NUMBER(-7,"页码小于1"),
    NEED_LOGIN(-8,"需要登录"),

    //数据库增删改
    DELETE_SUCCESS(100,"删除成功"),

    //用户相关
    LOGIN_FAILED(1000,"登录失败"),
    WRONG_USERNAME_OR_PASSWORD(1001,"账号或者密码错误"),
    WRONG_PASSWORD(1003,"密码错误"),
    CANT_FIND_USER(1002,"找不到用户"),

    //菜单相关
    DISH_IS_NOT_EXIST(10000,"无法找到菜品"),
    CANT_DELETE_NOT_BELONG_YOU_TYPE(10001,"不能删除不属于你的类型"),

    //订单相关
    CANT_FIND_ORDER(11000,"找不到订单"),
    ORDER_OVER(11001,"订单已经结束"),
    DISH_HAS_NO_MORE(11002,"该菜品已经删除完了"),
    HAS_ACTIVE_ORDER(11003,"有未完成的订单"),
    CREATE_ORDER_FAILED(11004,"创建订单失败"),
    CANT_FIND_ACTIVE_ORDER(11005,"当前暂未有正在进行的订单"),
    PAY_FAILED(11006,"支付失败"),

    //餐厅相关
    DELETE_RESTAURANT_FAILED(12000,"删除餐厅失败"),
    CANT_FIND_RESTAURANT(12001,"找不到餐厅"),
    CANT_FIND_USER_RESTAURANT(12002,"找不到餐厅，或者该餐厅不属于该用户"),

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
