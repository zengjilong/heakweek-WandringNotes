package com.travelsnotes.util;

import java.util.Calendar;
import java.util.Date;

public class TodayLoginUtil {

    static public boolean getToday(Date date){
        Calendar c1 = Calendar.getInstance();

        c1.setTime(date);

        int year1 = c1.get(Calendar.YEAR);

        int month1 = c1.get(Calendar.MONTH)+1;

        int day1 = c1.get(Calendar.DAY_OF_MONTH);

        Calendar c2 = Calendar.getInstance();

        c2.setTime(new Date());

        int year2 = c2.get(Calendar.YEAR);

        int month2 = c2.get(Calendar.MONTH)+1;

        int day2 = c2.get(Calendar.DAY_OF_MONTH);

        if(year1 == year2 && month1 == month2 && day1 == day2){
            return true;
        }
        return false;
    }
}
