package com.lzx2005.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.OrderService;
import com.lzx2005.tools.SecretTools;
import com.lzx2005.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/10.
 */
@RestController
@RequestMapping(value = "/order",produces = "application/json; charset=utf-8")
public class OrderController {

    @Autowired
    private OrderService orderService;

}
