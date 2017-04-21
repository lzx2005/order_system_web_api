package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Restaurant;
import com.lzx2005.entity.User;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.MenuService;
import com.lzx2005.service.RestaurantService;
import com.lzx2005.service.UserService;
import com.lzx2005.tools.PointTools;
import com.lzx2005.tools.SUID;
import com.lzx2005.tools.StringTools;

import com.mongodb.client.model.geojson.CoordinateReferenceSystem;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/11.
 */
@RestController
@RequestMapping(value = "/console/rest",produces = "application/json; charset=utf-8")
public class ConsoleRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    /**
     *
     * ------------------餐厅管理----------------------
     *
     * */

    @RequestMapping(value = "/restaurant/create",method = RequestMethod.POST)
    @ResponseBody
    public String createRestaurant(HttpServletRequest request,
                                   @RequestParam(defaultValue = "",required = true) String name,
                                   @RequestParam(defaultValue = "",required = true) double lng,
                                   @RequestParam(defaultValue = "",required = true) double lat){
        if(StringTools.hasEmpty(name,lng+"",lat+"")){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult().toString();
        }


        double[] doubles = PointTools.bd09_To_Gcj02(lat, lng);
        List<Double> list = new ArrayList<>();
        list.add(doubles[0]);
        list.add(doubles[1]);
        User user = (User) request.getAttribute("user");
        Restaurant restaurant = new Restaurant();
        restaurant.setPosition(list);
        restaurant.setRestaurantName(name);
        restaurant.setRestaurantId(SUID.getUUID());
        restaurant.setBelong(user.getUserId());
        restaurant.setCreateTime(new Date());
        return restaurantService.createRestaurant(restaurant).toString();
    }

    /**
     *
     * ------------------菜品管理----------------------
     *
    * */

    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
    @ResponseBody
    public String createDish(@RequestParam(defaultValue = "",required = true) String name,
                             @RequestParam(defaultValue = "",required = true) double price,
                             @RequestParam(defaultValue = "",required = true) String belongRest,
                             @RequestParam(defaultValue = "",required = true) long type,
                             HttpServletRequest request){
        if(StringTools.hasEmpty(name,price+"",type+"",belongRest)){
            return ServiceResultEnum.NEED_PARAMS.toServiceResult().toString();
        }
        User user = (User) request.getAttribute("user");
        ServiceResult serviceResult = menuService.createDish(name,price,0,type,user.getUserId(),belongRest);
        return serviceResult.toString();
    }


    @RequestMapping(value = "/dish/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDish(@RequestParam("dishId")long dishId){
        //todo 删除菜品
        return null;
    }


    /**
     *
     * ------------------菜品类型管理----------------------
     *
     * */

    @RequestMapping(value = "/dishType/getAllMyDishType",method = RequestMethod.GET)
    @ResponseBody
    public String getAllMyDishType(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        ServiceResult serviceResult = menuService.getAllDishTypeByUserId(user.getUserId());
        return serviceResult.toString();
    }

    @RequestMapping(value = "/dishType/create",method = RequestMethod.POST)
    @ResponseBody
    public String createDishType(@RequestParam("typeName")String typeName,
                                 HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        ServiceResult dishType = menuService.createDishType(typeName, user.getUserId());
        return dishType.toString();
    }

    @RequestMapping(value = "/dishType/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDishType(@RequestParam("typeId")int typeId,
                                 HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        ServiceResult serviceResult = menuService.removeDishType(typeId, user.getUserId());
        return serviceResult.toString();
    }
}
