package com.dhu.service.StaticService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTime {
    public static Date getNowTime()
    {
        Calendar calendar=Calendar.getInstance();
        return calendar.getTime();
    }
}
