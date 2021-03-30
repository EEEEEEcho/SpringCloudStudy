package com.echo.service.service;

import com.echo.service.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public List<User> queryAllUser();

    public User queryUserById(@Param("id") Long id);
}
