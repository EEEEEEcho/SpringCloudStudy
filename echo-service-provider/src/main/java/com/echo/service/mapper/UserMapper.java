package com.echo.service.mapper;

import com.echo.service.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> queryAllUser();

    public User queryUserById(@Param("id") Long id);
}
