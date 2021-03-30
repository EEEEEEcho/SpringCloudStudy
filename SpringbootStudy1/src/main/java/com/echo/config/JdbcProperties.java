package com.echo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 专门读取资源文件的类
 */
@Component      //目前springboot2.4版本需要在@ConfigurationProperties注解上添加@Component注解
@ConfigurationProperties(prefix = "jdbc")   //@ConfigurationProperties获取application.properties中以jdbc开头的配置信息
public class JdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    //spring会自动调用getter和setter方法对上述四个属性进行设置，而不再需要@Value注解
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
