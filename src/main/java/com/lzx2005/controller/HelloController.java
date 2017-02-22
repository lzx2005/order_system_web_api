package com.lzx2005.controller;

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

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello,"+new Date();
    }
}
