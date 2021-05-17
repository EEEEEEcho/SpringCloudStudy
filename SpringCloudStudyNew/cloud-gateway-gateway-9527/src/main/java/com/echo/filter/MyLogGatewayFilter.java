package com.echo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************come in MyLogGatewayFilter : " + new Date());
        //从请求参数中取出来username
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        //如果username为空
        if(username == null){
            //日志提示
            log.info("**********用户名为null,非法用户**********");
            //过滤器设置一个响应
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //过滤器返回这个响应，请求结束
            return exchange.getResponse().setComplete();
        }
        //正常情况，将请求转发给下一个过滤器
        return chain.filter(exchange);
    }

    //该过滤器的优先级
    @Override
    public int getOrder() {
        return 0;
    }
}
