package com.lzx2005.controller;


import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Order;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.MenuService;
import com.lzx2005.service.OrderService;
import com.lzx2005.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lizhengxian on 2017/3/6.
 */

@Controller
@RequestMapping(value = "/rest",produces = "application/json;charset=UTF-8")
public class RestController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/restaurant/near",method = RequestMethod.GET)
    @ResponseBody
    public String getNearRestaurant(double lng,double lat,double length){
        return restaurantService.getNearRestaurant(lng,lat,length).toString();
    }

    @RequestMapping(value = "/restaurant/info",method = RequestMethod.GET)
    @ResponseBody
    public String getRestaurantById(String restaurantId){
        return restaurantService.findByRestaurentId(restaurantId).toString();
    }

    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
    @ResponseBody
    public String createDish(HttpServletRequest request,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = true) double price,
                             @RequestParam(required = true) long logo,
                             @RequestParam(required = true) String belongRest,
                             @RequestParam(required = true) long type){

        int userId = (int)request.getAttribute("userId");
        ServiceResult dish = menuService.createDish(name, price, logo, type, userId,belongRest);
        logger.info(dish.toString());
        return dish.toString();

    }

    @RequestMapping(value = "/dish/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDish(HttpServletRequest request,
                             @RequestParam(value = "dishId",defaultValue = "0",required = true)long dishId){
        if(dishId<=0){
            return ServiceResultEnum.NEED_PARAMS.toString();
        }
        ServiceResult serviceResult = menuService.removeDish(dishId);
        return serviceResult.toString();
    }


    @RequestMapping(value = "/dish/all",method = RequestMethod.GET)
    @ResponseBody
    public String getDish(HttpServletRequest request,
                          @RequestParam(value = "page",required = false,defaultValue = "0") int page){
        ServiceResult dishes = menuService.getDishAll(page);
        return dishes.toString();
    }

    @RequestMapping(value = "/dish/getByRestId",method = RequestMethod.GET)
    @ResponseBody
    public String getDishByRest(String restId){
        return menuService.getAllDishByRestId(restId).toString();
    }


    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(HttpServletRequest request,@RequestBody Order order){
        int userId = (int)request.getAttribute("userId");
        order.setUserId(userId);
        return orderService.createOrder(order).toString();
    }


    @RequestMapping(value = "/order/find",method = RequestMethod.GET)
    @ResponseBody
    public String findOrder(HttpServletRequest request){
        int userId = (int)request.getAttribute("userId");
        return orderService.findActivityOrder(userId).toString();
    }


    @RequestMapping(value = "/order/pay",method = RequestMethod.POST)
    @ResponseBody
    public String payOrder(HttpServletRequest request){
        int userId = (int)request.getAttribute("userId");
        return orderService.payOrder(userId).toString();
    }
}
