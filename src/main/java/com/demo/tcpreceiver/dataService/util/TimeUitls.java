package com.demo.tcpreceiver.dataService.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TimeUitls {

    public static LocalDateTime getDatetimeStr(byte[] bytes) {
        long centurySeconds = ((long)(bytes[0] & 0xFF) << 24) |
                ((long)(bytes[1] & 0xFF) << 16) |
                ((long)(bytes[2] & 0xFF) << 8) |
                (bytes[3] & 0xFF);
        long unixTimeInMillis = (centurySeconds - 946684800L) * 1000L;
        Date date = new Date(unixTimeInMillis);

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        LocalDateTime datetimeStr = LocalDateTime.parse(dateFormat.format(date));


        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println(localDateTime);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime datetime = LocalDateTime.parse(datetimeStr, formatter);
//        System.out.println(datetime);
        return localDateTime;
    }
}
