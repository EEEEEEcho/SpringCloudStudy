package com.echo.config;

import com.echo.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的配置
 * 1.声明该类是一个java配置类
 * 2.实现WebMvcConfigurer接口
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注入拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
        //设置拦截路径
        interceptorRegistration.addPathPatterns("/**");
    }
}
