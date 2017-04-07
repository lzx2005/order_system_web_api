package com.lzx2005.interceptors;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.dto.Token;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.tools.StringTools;
import com.lzx2005.tools.TokenTools;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        System.out.println(true);
        if(StringTools.isEmpty(token)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            ServiceResult serviceResult = ServiceResultEnum.NEED_TOKEN.toServiceResult();
            response.getWriter().write(serviceResult.toString());
            return false;
        }
        //解析token
        Token tokenVo = TokenTools.parseToken(token);
        if(tokenVo!=null&&tokenVo.getAccount()!=null&&tokenVo.getUserId()!=null){
            //通过验证
            request.setAttribute("userId",tokenVo.getUserId());
            request.setAttribute("username",tokenVo.getAccount());
        }else{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            ServiceResult serviceResult = ServiceResultEnum.PARSE_TOKEN_ERROR.toServiceResult();
            response.getWriter().write(serviceResult.toString());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
