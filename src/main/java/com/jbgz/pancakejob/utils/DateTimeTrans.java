package com.jbgz.pancakejob.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTrans {
    public static String datetimeToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDatetime = sdf.format(date);
        return strDatetime;
    }

    public static String timeToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strTime = sdf.format(date);
        return strTime;
    }

    public static String dateToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(date);
        return strDate;
    }

    public static Date stringToDate(String str)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = null;
        try {
            myDate = sdf.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return myDate;
    }

//    public static Date myToDate_1(String str)
//    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        Date myDate = null;
//        try {
//            myDate = sdf.parse(str);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return myDate;
//    }
}
