package com.xingtan.common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {
    public static SimpleDateFormat formatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddHHmmssS");

//    public static LocalDateTime fromDateToLocalDateTime(Date date) {
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
//        return instant.atZone(zoneId).toLocalDateTime();
//    }
//
//    public static String formatDateToLocalDateTime(Date date, String pattern) {
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        return formatter.format(localDateTime);
//    }
}
