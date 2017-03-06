package com.lzx2005.controller;

import com.lzx2005.dto.ApiRestResult;
import com.lzx2005.entity.Dish;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/6.
 */

@Controller
@RequestMapping("/rest")
public class RestController {

    @RequestMapping(value = "/dish/all",method = RequestMethod.GET)
    public ApiRestResult<List<Dish>> getDish(HttpServletRequest request,
                                             @RequestParam(value = "page",required = false,defaultValue = "1") int page){
        return null;
    }

}
