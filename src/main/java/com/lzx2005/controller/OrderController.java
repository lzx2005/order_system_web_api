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

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(HttpServletRequest request){
        Integer userId = (Integer)request.getAttribute("userId");
        if(userId==null){
            return ServiceResultEnum.NEED_PARAMS.toString();
        }
        ServiceResult order = orderService.createOrder(userId);
        return order.toString();
    }


    @RequestMapping(value = "/dish/add",method = RequestMethod.POST)
    @ResponseBody
    public String addDish(HttpServletRequest request,
                            @RequestParam(value = "dishId",defaultValue = "0") long dishId){
        Integer userId = (Integer)request.getAttribute("userId");
        ServiceResult byUserId = orderService.findByUserId(userId);
        Object data = byUserId.getData();
        if(data instanceof List){
            List list = (List)data;
            if(list.size()>0){
                JSONObject order = (JSONObject)list.get(0);
                if(order!=null){
                    String orderId = order.getString("orderId");
                    ServiceResult serviceResult = orderService.addDish(orderId, dishId);
                    return serviceResult.toString();
                }else{
                    return ServiceResultEnum.CANT_FIND_ORDER.toString();
                }
            }else{
                //找不到订单
                return ServiceResultEnum.CANT_FIND_ORDER.toString();
            }
        }else{
            return ServiceResultEnum.SERVICE_ERROR.toString();
        }
    }



    @RequestMapping(value = "/dish/remove",method = RequestMethod.POST)
    @ResponseBody
    public String removeDish(HttpServletRequest request,
                          @RequestParam(value = "dishId",defaultValue = "0") long dishId){
        Integer userId = (Integer)request.getAttribute("userId");
        ServiceResult byUserId = orderService.findByUserId(userId);
        Object data = byUserId.getData();
        if(data instanceof List){
            List list = (List)data;
            if(list.size()>0){
                JSONObject order = (JSONObject)list.get(0);
                if(order!=null){
                    String orderId = order.getString("orderId");
                    ServiceResult serviceResult = orderService.removeDish(orderId, dishId);
                    return serviceResult.toString();
                }else{
                    return ServiceResultEnum.CANT_FIND_ORDER.toString();
                }
            }else{
                //找不到订单
                return ServiceResultEnum.CANT_FIND_ORDER.toString();
            }
        }else{
            return ServiceResultEnum.SERVICE_ERROR.toString();
        }
    }


}
