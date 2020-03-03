package com.hdsx.appservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 梁铖城
 * 2020年03月03日10:28:27
 * 日期的简单的转换
 */
public class DateParseUtil {

    public static Date longToDate(long currentTime, String formatType) throws ParseException {
        // 根据long类型的毫秒数生命一个date类型的时间
        Date dateOld = new Date(currentTime);
        // 把date类型的时间转换为string
        String sDateTime = new SimpleDateFormat(formatType).format(dateOld);
        // 把String类型转换为Date类型
        return new SimpleDateFormat(formatType).parse(sDateTime);
    }

    public static Date getDateByShijianchuo(String shijianchuoStr) {
       try {
           if (shijianchuoStr != null && !shijianchuoStr.equals("")) {
               long time = Long.parseLong(shijianchuoStr);
               if (time != 0) {
                   String sdf = "yyyy-MM-dd HH:mm:ss";
                   Date date = longToDate(time, sdf);
                  return date;
               } else {
                   return new Date();
               }
           }
           return new Date();
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new Date();
    }

    public static String getShijianchuoByDateStr(String nyrStr) {
        try {
            if (nyrStr != null && !nyrStr.equals("")) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = dateFormat.parse(nyrStr);
                return parse.getTime() + "";
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
