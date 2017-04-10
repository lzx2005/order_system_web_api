package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lizhengxian on 2017/4/10.
 */
@RestController
@RequestMapping(value = "/order",produces = "application/json; charset=utf-8")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(HttpServletRequest request){
        String userIdStr = (String)request.getAttribute("userId");
        int userId = Integer.parseInt(userIdStr);
        ServiceResult order = orderService.createOrder(userId);
        return order.toString();
    }
}
