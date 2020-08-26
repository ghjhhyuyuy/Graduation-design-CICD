package com.zxk.jenkinsapi.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    /**
     * //获取系统当前时间
     * @return
     */
    public static String getTimeNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        String date = format.format(dateNow);//将dateNow格式化
        return date;
    }
}
