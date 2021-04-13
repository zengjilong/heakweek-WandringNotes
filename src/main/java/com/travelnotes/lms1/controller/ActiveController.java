package com.travelnotes.lms1.controller;

import com.travelnotes.lms1.mapper.HKMapper;
import com.travelnotes.lms1.pojo.UserActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveController {

    @Autowired
    private HKMapper userActiveProperties;

    private UserActive userActive;

    @GetMapping("/getUserActive")
    public UserActive getActive() {
        userActive = new UserActive();
        //前端获得token 获得userId 返回对应user信息
        int tempId = 7;
        try{
           userActive.setActiveDays(userActiveProperties.getActiveDays(tempId)) ;
           userActive.setUserName(userActiveProperties.getUsername(tempId));
           userActive.setTxtNum(userActiveProperties.getTxtNum(tempId));
        }catch(Exception e){
            return null;
        }
        return userActive;
    }

}
