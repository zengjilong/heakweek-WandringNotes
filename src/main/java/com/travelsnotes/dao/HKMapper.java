package com.travelsnotes.dao;

import com.travelsnotes.pojo.UserActive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HKMapper {

    int setUsername(@Param("userName") String userName,@Param("id")  int id);

    int setPhoneNumber(@Param("phoneNumber")String phoneNumber,@Param("id") int id);

    String getPicText(int id);

    int setAvatar(@Param("avatar")String avatar, @Param("id")int id);

    String getUsername(int id);

    int getActiveDays(int id);

    int getTxtNum(int id);

}
