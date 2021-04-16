package com.travelsnotes.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    public UserInfo(String userName, String password, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    int userId;
    String userName;
    String password;
    String phoneNumber;
    String avatarUrl;
    int activeDays;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型 yyyy-MM-dd HH:mm:ss,
    Date recentlyLogin;
}
