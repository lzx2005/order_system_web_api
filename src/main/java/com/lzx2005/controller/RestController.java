package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/rest")
public class RestController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
    @ResponseBody
    public String createDish(HttpServletRequest request,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = true) double price,
                             @RequestParam(required = true) long belong,
                             @RequestParam(required = true) long logo,
                             @RequestParam(required = true) long type){

        ServiceResult<Dish> dish = menuService.createDish(name, price, logo, type, belong);
        System.out.println(dish);
        return dish.toString();

    }


    @RequestMapping(value = "/dish/all",method = RequestMethod.GET)
    @ResponseBody
    public String getDish(HttpServletRequest request,
                          @RequestParam(value = "page",required = false,defaultValue = "0") int page){
        ServiceResult<Page<Dish>> dishes = menuService.getDishAll(page);
        return dishes.toString();
    }

}
