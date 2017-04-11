package com.lzx2005.interceptors;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.dto.Token;
import com.lzx2005.entity.User;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.tools.StringTools;
import com.lzx2005.tools.TokenTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
@Component
public class ConsoleLoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleLoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/console/login");
            return false;
        }else{
            String servletPath = request.getServletPath();
            logger.info("用户{}访问{}",user.getUsername(),servletPath);
            request.setAttribute("action",servletPath);
            request.setAttribute("user",user);
            return super.preHandle(request, response, handler);
        }
    }
}
