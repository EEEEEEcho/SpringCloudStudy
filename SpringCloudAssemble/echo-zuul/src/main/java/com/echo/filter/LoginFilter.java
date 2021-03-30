package com.echo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component      //加入到spring容器中 即可生效
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器的类型：
     * @return pre | route | post | error
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，返回值越小，优先级越高
     * @return 优先级
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 是否执行该过滤器,执行定义的run方法
     * @return 是|否
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //初始化zuul网关的 context上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = currentContext.getRequest();
        //获取请求中携带的参数
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            //拦截，不转发请求
            currentContext.setSendZuulResponse(false);
            //设置响应状态码为401
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            //设置响应体
            currentContext.setResponseBody("request error !");
        }
        //返回值为null代表什么都不做
        return null;
    }
}
