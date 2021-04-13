package com.travelsnotes.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    public UserInfo(String userName, String password, String phoneNumber) {
        this.userName = userName;
        this.userPassword = password;
        this.usertele = phoneNumber;
    }
    int userId;
    String userName;
    String userPassword;
    String usertele;
    String avatarUrl;
}
