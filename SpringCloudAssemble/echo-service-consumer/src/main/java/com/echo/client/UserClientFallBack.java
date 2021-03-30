package com.echo.client;

import com.echo.pojo.User;
import org.springframework.stereotype.Component;

@Component  //注入到spring容器
public class UserClientFallBack implements UserClient{
    /**
     * 注意，这个类实现FeignClient的接口目的是为了实现熔断，而不是实现某些功能，
     * 重写接口中的方法的实现就是熔断方法的实现
     * @param id
     * @return
     */
    @Override
    public User queryUserById(Long id) {
        User user = new User();
        user.setUserName("服务器阵亡了，稍后再试");
        return user;
    }
}
