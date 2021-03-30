package com.echo.mapper;

import com.echo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//声明这是一个mapper，不再实现接口扫描。扫描现在由引导类扫描
//1.使用通用mapper版
//@Mapper
//public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
//    //继承通用mapper整合mybatis
//}
//2.不适用通用mapper版
@Mapper //声明这是一个mapper，不再实现接口扫描。扫描现在由引导类扫描
public interface UserMapper{
    public User selectByPrimaryKey(@Param("id") Long id);

    public void deleteByPrimaryKey(@Param("id")Long id);

    public List<User> queryUserAll();
}
