package com.travelsnotes.controller;


import com.travelsnotes.dao.HKMapper;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.service.OSSutil;
import com.travelsnotes.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserInfoController {

    @Autowired
    private HKMapper userProperties;

    @Autowired
    private OSSutil ossProperties;

    @Autowired
    FileUploadUtil fileUtil;

    //设置用户信息
    @PostMapping("/updateInfo")
    public Result setUserInfo(@RequestParam(value = "token") String token,
                              @RequestParam(value = "userName")String userName,
                              @RequestParam(value = "phoneNumber",required = false) String phoneNumber,
                              @RequestParam(value = "avatar",required = false) MultipartFile file,
                                HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(token);
        if (attribute == null) {
            return null;
        }
        String avatarUrl=null;
        if (file!=null) avatarUrl= fileUtil.uploadPage(request, file);
        int tempId = (int) attribute;
        try{
            userProperties.setUsername(phoneNumber, tempId);
            userProperties.setPhoneNumber(userName, tempId);
            userProperties.setAvatar(avatarUrl, tempId);
        }catch(Exception e){
            return Result.error(ResultCodeEnum.FAIL_UPDATE);
        }
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    //设置用户头像URL
    @PostMapping("/uploadAvatar")
    public String setUserAvatar(UserInfo userInfo){
        int tempId = 1;
        try{
            if(ossProperties.setAvatar(userInfo.getAvatarUrl(), tempId)){
                return "Result! Get";
            }else{
                return "Failed!";
            }
        }catch(Exception e){
            return "Catch Exception";
        }
    }

    //返回用户头像URL
    @GetMapping("/user/getUserAvatar")
    public String getUserInfo(Integer id){
        int tempId = 1;
        return ossProperties.getAvatar(tempId);
    }

}
