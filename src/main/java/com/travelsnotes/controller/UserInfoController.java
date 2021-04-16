package com.travelsnotes.controller;


import com.travelsnotes.dao.HKMapper;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.service.OSSutil;
import com.travelsnotes.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoController {

    @Autowired
    private HKMapper userProperties;

    @Autowired
    private OSSutil ossProperties;

    @Autowired
    FileUploadUtil fileUtil;

    //设置用户信息
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @CrossOrigin
    public Result setUserInfo(@RequestPart(value = "avatar",required = false) MultipartFile file,
                              @RequestParam(value = "token") String token,
                              @RequestParam(value = "userName",required = false)String userName,
                              @RequestParam(value = "phoneNumber",required = false) String phoneNumber,
                                HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(token);
        if (attribute == null) {
            return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);
        }
        System.out.println(file==null);
       String avatarUrl= fileUtil.uploadPage(request, file);
        System.out.println(avatarUrl);
        int tempId = (int) attribute;
        try{
            if (userName!=null&&userName.length()!=0)
            userProperties.setUsername(userName, tempId);
            if (phoneNumber!=null&&phoneNumber.length()!=0)
            userProperties.setPhoneNumber(phoneNumber, tempId);
            if (avatarUrl!=null&&avatarUrl.length()!=0)
            userProperties.setAvatar(avatarUrl, tempId);
            else return Result.ok(ResultCodeEnum.FAIL_SAVEFILE);
        }catch(Exception e){
            return Result.error(ResultCodeEnum.FAIL_UPDATE);
        }
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    //设置用户头像URL
    @RequestMapping(value = "/uploadAvatar",method = RequestMethod.POST)
    @CrossOrigin
    public Result setUserAvatar( @RequestPart( value = "avatar") MultipartFile file,
                                 @RequestParam(value = "token") String token,
                                HttpServletRequest request) {
        try {
            Object attribute = request.getSession().getAttribute(token);
            if (attribute == null) {
                return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);
            }
            String avatarUrl = fileUtil.uploadPage(request, file);
            int tempId = (int) attribute;
            if (avatarUrl == null||avatarUrl.length()==0) return Result.error(ResultCodeEnum.FILE_UPLOAD_ERROR);
            userProperties.setAvatar(avatarUrl,tempId);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    //返回用户头像URL
    @RequestMapping(value = "/user/getUserAvatar",method = RequestMethod.POST)
    @CrossOrigin
    public Result getUserInfo(@RequestBody Map<String,String> param,HttpServletRequest request) {
        try {
            Object attribute = request.getSession().getAttribute(param.get("token"));
            if (attribute == null) {
                return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);
            }
            int tempId = (int) attribute;
            Map<String,Object> map=new HashMap<>();
            map.put("avatar",userProperties.getAvatar(tempId));
            return Result.ok().data(map);
        } catch (Exception e) {
            return Result.error();
        }
    }
}
