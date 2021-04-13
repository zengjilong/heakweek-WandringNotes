package com.travelsnotes.service;


import com.travelsnotes.dao.UserMapper;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfo queryById(int id) {
        return userMapper.queryById(id);
    }

    @Override
    public UserInfo queryByName(String name) {
        return userMapper.queryByName(name);
    }

    @Override
    public int registerUser(UserInfo userInfo) {
        return  userMapper.registerUser(userInfo);
    }
}
