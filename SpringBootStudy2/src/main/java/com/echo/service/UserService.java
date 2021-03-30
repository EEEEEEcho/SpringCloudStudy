package com.echo.service;

import com.echo.mapper.UserMapper;
import com.echo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Transactional //加入事务 ，这就完成了整合事务
    public void deleteUserById(Long id){
        this.userMapper.deleteByPrimaryKey(id);
    }

    public List<User> queryUserAll(){
        return this.userMapper.queryUserAll();
    }
}
