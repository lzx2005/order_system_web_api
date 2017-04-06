package com.lzx2005.controller;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.service.UserService;
import com.lzx2005.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lizhengxian on 2017/4/6.
 */

@RestController
@RequestMapping(value = "/user",produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam String username,
                        @RequestParam String password){
        if(StringTools.hasEmpty(username,password)){
            return ServiceResultEnum.NEED_PARAMS.toString();
        }
        ServiceResult login = userService.login(username, password);
        return login.toString();
    }
}
