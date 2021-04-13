package com.travelsnotes.service;


import com.travelsnotes.pojo.UserInfo;

public interface UserService {
    public UserInfo queryById(int id);
    public UserInfo queryByName(String name);
    public int registerUser(UserInfo userInfo);
}
