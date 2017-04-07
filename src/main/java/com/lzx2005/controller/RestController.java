package com.lzx2005.controller;


import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lizhengxian on 2017/3/6.
 */

@Controller
@RequestMapping(value = "/rest",produces = "application/json;charset=UTF-8")
public class RestController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
    @ResponseBody
    public String createDish(HttpServletRequest request,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = true) double price,
                             @RequestParam(required = true) long logo,
                             @RequestParam(required = true) long type){

        String userIdString = (String)request.getAttribute("userId");
        int userId = Integer.parseInt(userIdString);
        ServiceResult dish = menuService.createDish(name, price, logo, type, userId);
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

}
