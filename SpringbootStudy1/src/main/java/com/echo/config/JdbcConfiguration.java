package com.echo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 该类用来进行数据源的配置
 */
@Configuration  //声明这个类是一个java配置类,使这个类相当于一个xml配置文件
//@PropertySource("classpath:jdbc.properties")    // @PropertySource读取资源文件,一般使用一个资源读取类来单独读取，不用这种方式
@EnableConfigurationProperties(JdbcProperties.class)  //启用属性读取类
public class JdbcConfiguration {

//    @Value("${jdbc.driverClassName")    //@Value 获取资源文件中的值
//    private String driverClassName;
//    @Value("jdbc.url=jdbc:mysql://127.0.0.1:3306/leyou")
//    private String url;
//    @Value("jdbc.username=root")
//    private String username;
//    @Value("jdbc.password=s814466057")
//    private String password;
    @Autowired
    private JdbcProperties jdbcProperties;

    @Bean // 该注解将方法的返回值，注入到spring容器中 相当于xml中的<bean>标签
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        druidDataSource.setUrl(jdbcProperties.getUrl());
        druidDataSource.setUsername(jdbcProperties.getUsername());
        druidDataSource.setPassword(jdbcProperties.getPassword());
        return druidDataSource;
    }
}
