package com.travelsnotes.controller;


import com.travelsnotes.dao.HKMapper;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserActive;
import com.travelsnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ActiveController {

    @Autowired
    private HKMapper mapper;


    @Autowired
    UserService userService;
    @GetMapping("/getUserActive")
    public Result getActive(@RequestParam(value = "token")String token, HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(token);
        if (attribute == null) {
            return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);
        }
        Map<String,Object> map=new HashMap<>();
        int tempId=(int)attribute;
        //前端获得token 获得userId 返回对应user信息
        try {
            map.put("userName",mapper.getUsername(tempId));
           map.put("ActiveDays",new Integer(mapper.getActiveDays(tempId)));
           map.put("txtNum",new Integer(mapper.getTxtNum(tempId)));
        } catch (Exception e) {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.ok().data(map);
    }

}
