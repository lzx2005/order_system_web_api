package com.lzx2005.config;

import com.lzx2005.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("加载拦截器"+loginInterceptor);
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(
                        "/rest/**"
                )
                .excludePathPatterns(

                )
        ;
    }
}
