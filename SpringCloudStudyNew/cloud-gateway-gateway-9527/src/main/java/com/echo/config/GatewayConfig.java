package com.echo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        //相当于yml中的routes 配置
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //第一个参数是这个路由的ID
        //第二个是一个函数型参数，定义了一个路径，以及该路径路由到哪个URL,路由到了百度新闻。。。
        //当访问http://localhost:9527/guonei的时候会路由到http://news.baidu.com/guonei
        routes.route("path_route_echo1",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")
        ).build();

        //第一个参数是这个路由的ID
        //第二个是一个函数型参数，定义了一个路径，以及该路径路由到哪个URL,路由到了百度新闻。。。
        //当访问http://localhost:9527/guoji的时候会路由到http://news.baidu.com/guoji
        routes.route("path_route_echo2",
                r -> r.path("/guoji").uri("http://news.baidu.com/guoji")
        ).build();
        return routes.build();
    }
}
