package com.chris.bulleyeadmin.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    public static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static int getDayOfMouth() {
        Calendar a = Calendar.getInstance(Locale.CHINA);
        int day = a.getActualMaximum(5);
        return day;
    }

    public static String getCurrentTime(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String date = formatter.format(currentTime);
        return date;
    }

    public static Date getNowDate (){
        Date date = new Date();
        return date;
    }

    public static String getCurrentYearMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String date = formatter.format(currentTime);
        return date;
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String date = formatter.format(currentTime);
        return date;
    }

    public static String getCurrentYear() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String date = formatter.format(currentTime);
        return date;
    }

    public static Date stringToDate(String date) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Date toStandarDate(String date, String format) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat(format);
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Date toStandarDatehms(String date) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(currentTime);
        return date;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = formatter.format(date);
        return date1;
    }

    public static Map<String, String> getStringParams(HttpServletRequest request) {
        Map<String, String[]> rawParam = request.getParameterMap();
        Map<String, String> keyParam = new HashMap();
        for (String key : rawParam.keySet()) {
            if (rawParam.get(key) != null) {
                String[] value = (String[]) rawParam.get(key);
                if ((value != null) && (value.length == 1)) {
                    keyParam.put(key, value[0]);
                }
            }
        }
        return keyParam;
    }

    public static String toJson(HttpServletRequest request)
            throws Exception {
        int clen = request.getContentLength();
        if (clen > 0) {
            byte[] buffer = new byte[clen];
            for (int i = 0; i < clen; i++) {
                int readlen = request.getInputStream().read(buffer, i, clen - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }
            return new String(buffer, request.getCharacterEncoding());
        }
        return null;
    }

    public static String getMinutes(String startDate, String endDate)
            throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        long from = simpleFormat.parse(startDate).getTime();
        long to = simpleFormat.parse(endDate).getTime();
        return String.valueOf((to - from) / 60000L);
    }
}
