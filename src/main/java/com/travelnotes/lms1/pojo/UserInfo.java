package com.travelnotes.lms1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    int userId;
    private String userName;
    private String userPassword;
    private String phoneNumber;
    private String avatarUrl;

}
