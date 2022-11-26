package com.dhu.service.staticService;

import java.util.Calendar;
import java.util.Date;

public class MyTime {
    public static Date getNowTime()
    {
        Calendar calendar=Calendar.getInstance();
        return calendar.getTime();
    }
}
