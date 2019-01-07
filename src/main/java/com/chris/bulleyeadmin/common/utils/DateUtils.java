package com.chris.bulleyeadmin.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    public static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static int GetdayofManth() {
        Calendar a = Calendar.getInstance(Locale.CHINA);
        int day = a.getActualMaximum(5);
        return day;
    }

    public static String getNow(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String date = formatter.format(currentTime);
        return date;
    }

    public static String getYearMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String date = formatter.format(currentTime);
        return date;
    }

    public static String getMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String date = formatter.format(currentTime);
        return date;
    }

    public static String getYear() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String date = formatter.format(currentTime);
        return date;
    }

    public static Date toStandarDate(String date) {
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

    public static String getNow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(currentTime);
        return date;
    }

    public static Date getNowDate() {
        Date currentTime = new Date();
        return currentTime;
    }

    public static String stringToDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = formatter.format(date);
        return date1;
    }

    public static List<String> findalldates(String startdate, String enddate) {
        List<String> list = new ArrayList();
        list.add(startdate);
        Calendar calstart = Calendar.getInstance();
        calstart.setTime(toStandarDate(startdate));
        Calendar calend = Calendar.getInstance();
        calend.setTime(toStandarDate(enddate));
        while (toStandarDate(enddate).after(calstart.getTime())) {
            calstart.add(5, 1);
            list.add(stringToDate(calstart.getTime()));
        }
        return list;
    }

    public static List<String> dateEveyweek(String mString) {
        Date mdate = toStandarDate(mString);
        int b = mdate.getDay();

        List<String> list = new ArrayList();
        Long fTime = Long.valueOf(mdate.getTime() - b * 24 * 3600000);
        for (int a = 1; a < 8; a++) {
            Date fDate = new Date();
            fDate.setTime(fTime.longValue() + a * 24 * 3600000);
            list.add(stringToDate(fDate));
        }
        return list;
    }

    public static List<String> dateToWeek(String mString) {
        Date mdate = toStandarDate(mString);
        int b = mdate.getDay();

        List<String> list = new ArrayList();
        Long fTime = Long.valueOf(mdate.getTime() - b * 24 * 3600000);
        for (int a = 1; a < 8; a++) {
            Date fDate = new Date();
            fDate.setTime(fTime.longValue() + a * 24 * 3600000);
            if (a == 1) {
                list.add(stringToDate(fDate));
            }
            if (a == 7) {
                list.add(stringToDate(fDate));
            }
        }
        return list;
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

    public static String getIpAdrress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if ((StringUtils.isNotEmpty(XFor)) && (!"unKnown".equalsIgnoreCase(XFor))) {
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            }
            return XFor;
        }
        XFor = Xip;
        if ((StringUtils.isNotEmpty(XFor)) && (!"unKnown".equalsIgnoreCase(XFor))) {
            return XFor;
        }
        if ((StringUtils.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if ((StringUtils.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((StringUtils.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if ((StringUtils.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ((StringUtils.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    public static String getMinutes(String startDate, String endDate)
            throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        long from = simpleFormat.parse(startDate).getTime();
        long to = simpleFormat.parse(endDate).getTime();
        return String.valueOf((to - from) / 60000L);
    }
}
