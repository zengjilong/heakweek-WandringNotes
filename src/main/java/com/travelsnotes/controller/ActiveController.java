package com.travelsnotes.controller;


import com.travelsnotes.dao.HKMapper;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserActive;
import com.travelsnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ActiveController {

    @Autowired
    private HKMapper mapper;


    @Autowired
    UserService userService;
    @RequestMapping(value = "/getUserActive",method = RequestMethod.POST)
    @CrossOrigin
    public Result getActive(@RequestBody Map<String,String> param, HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(param.get("token"));
        if (attribute == null) {
            return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);
        }
        Map<String,Object> map=new HashMap<>();
        int tempId=(int)attribute;
        //前端获得token 获得userId 返回对应user信息
        try {
            map.put("userName",mapper.getUsername(tempId));
           map.put("ActiveDays",new Integer(mapper.getActiveDays(tempId)));
            Object txtNum = mapper.getTxtNum(tempId);
            System.out.println(txtNum);
            if (txtNum==null) txtNum="0";
            System.out.println(txtNum);
            map.put("txtNum",txtNum);
        } catch (Exception e) {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.ok().data(map);
    }

}
