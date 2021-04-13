package com.travelsnotes.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HKMapper {

    String setUsername(String usrName, int id);

    String setPhoneNumber(String phoneNumber, int id);

    String getPicText(int id);

    String setAvatar(String avatar, int id);

    String getUsername(int id);

    int getActiveDays(int id);

    int getTxtNum(int id);

}
