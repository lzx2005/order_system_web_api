package com.lzx2005.interceptors;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.tools.StringTools;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getParameter("token");
        if(StringTools.isEmpty(token)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            ServiceResult serviceResult = ServiceResultEnum.NEED_TOKEN.toServiceResult();
            response.getWriter().write(serviceResult.toString());
            return false;
        }
        //todo 还没写完
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
