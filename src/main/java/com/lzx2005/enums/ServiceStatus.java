package com.lzx2005.enums;

import com.lzx2005.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/3/6.
 */
public enum ServiceStatus {
    //通用
    SUCCESS(0,"请求成功"),
    FAIL(-1,"请求失败"),
    SERVICE_ERROR(-2,"服务器出错"),
    DB_ERROR(-3,"数据库操作出错"),

    //菜单相关
    DISH_IS_NOT_EXIST(10000,"无法找到菜品");

    private int code;
    private String msg;

    ServiceStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
