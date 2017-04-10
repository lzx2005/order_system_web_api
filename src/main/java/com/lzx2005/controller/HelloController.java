package com.lzx2005.controller;

import com.lzx2005.entity.User;
import com.lzx2005.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Lizhengxian on 2017/2/22.
 */
@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        User user = new User();
        user.setUsername("lzx2005");
        user.setPassword("test");
        userRepository.save(user);
        return "hello,"+new Date();
    }

}
