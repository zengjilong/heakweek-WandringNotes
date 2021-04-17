package com.travelsnotes.service;


import com.travelsnotes.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserService {
    public UserInfo queryById(int id);
    public UserInfo queryByName(String name);
    public int registerUser( String userName,String password);
    public int setRecentLogin( int id,Date date);
    public int addActiveDays(int id);

}
