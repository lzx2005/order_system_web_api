package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.MenuService;
import com.lzx2005.service.RestaurantService;
import com.lzx2005.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lizhengxian on 2017/4/11.
 */
@Controller
@RequestMapping(value = "/console",produces = "text/html; charset=utf-8")
public class ConsoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }


    @RequestMapping(value = "/restaurant",method = RequestMethod.GET)
    public String restaurant(@RequestParam(value = "page",defaultValue = "0")int page,
                       HttpServletRequest request,
                       Model model){
        User user = (User) request.getAttribute("user");
        if(page<0){
            page=0;
        }
        ServiceResult serviceResult = restaurantService.getAllMyRestaurant(page,user.getUserId());
        //System.out.println(serviceResult.toString());
        model.addAttribute("restaurants",serviceResult);
        return "restaurant";
    }


    @RequestMapping(value = "/dish",method = RequestMethod.GET)
    public String dish(@RequestParam(value = "page",defaultValue = "0")int page,
                       HttpServletRequest request,
                       Model model){
        User user = (User) request.getAttribute("user");
        if(page<0){
            page=0;
        }
        ServiceResult serviceResult = menuService.getDishAllByUserId(page, user.getUserId());
        //System.out.println(serviceResult.toString());
        model.addAttribute("dishes",serviceResult);
        return "dish";
    }


    @RequestMapping(value = "/dishType",method = RequestMethod.GET)
    public String dishType(@RequestParam(value = "page",defaultValue = "0")int page,
                       HttpServletRequest request,
                       Model model){
        User user = (User) request.getAttribute("user");
        if(page<0){
            page=0;
        }
        ServiceResult serviceResult = menuService.getAllDishTypeByUserId(page, user.getUserId());
        model.addAttribute("dishTypes",serviceResult);
        return "dishType";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login/sub",method = RequestMethod.POST)
    public String loginSub(@RequestParam(value = "username",defaultValue = "")String username,
                           @RequestParam(value = "password",defaultValue = "")String password,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Model model){
        ServiceResult login = userService.consoleLogin(username, password);
        if (login.getCode()== ServiceResultEnum.SUCCESS.getCode()){
            User user = (User) login.getData();
            request.getSession().setAttribute("user",user);
            return "redirect:/console/welcome";
        }else{
            model.addAttribute("result",login);
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                        HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "redirect:/console/welcome";
    }
}
