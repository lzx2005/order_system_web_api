package com.lzx2005.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Lizhengxian on 2017/4/11.
 */
@Controller
@RequestMapping(value = "/console",produces = "text/html; charset=utf-8")
public class ConsoleController {

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }
}
