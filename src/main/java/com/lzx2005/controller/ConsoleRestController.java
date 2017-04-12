package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;
import com.lzx2005.service.MenuService;
import com.lzx2005.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
