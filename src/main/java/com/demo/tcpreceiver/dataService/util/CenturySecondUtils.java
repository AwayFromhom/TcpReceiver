package com.demo.tcpreceiver.dataService.util;

import java.util.Date;

public class CenturySecondUtils {
    public static byte[] toCenturySecondsBytes(Date date) {
        long unixTimeInMillis = date.getTime();
        long unixTimestampInSeconds = unixTimeInMillis / 1000L;
        long centurySeconds = (unixTimestampInSeconds - 946684800L) / 100L;
        return new byte[] {
                (byte) ((centurySeconds >> 24) & 0xFF),
                (byte) ((centurySeconds >> 16) & 0xFF),
                (byte) ((centurySeconds >> 8) & 0xFF),
                (byte) (centurySeconds & 0xFF)
        };
    }

    public static Date fromCenturySecondsBytes(byte[] bytes) {
        long centurySeconds = ((long)(bytes[0] & 0xFF) << 24) |
                ((long)(bytes[1] & 0xFF) << 16) |
                ((long)(bytes[2] & 0xFF) << 8) |
                (bytes[3] & 0xFF);
        long unixTimeInMillis = (centurySeconds - 946684800L) * 1000L;
        return new Date(unixTimeInMillis);
    }

    public static byte[] getCurrentCenturySecondsBytes() {
        long currentTimeInMillis = System.currentTimeMillis();
        long unixTimestampInSeconds = currentTimeInMillis / 1000L;
        long centurySeconds = (unixTimestampInSeconds - 946684800L) / 100L;
        return new byte[] {
                (byte) ((centurySeconds >> 24) & 0xFF),
                (byte) ((centurySeconds >> 16) & 0xFF),
                (byte) ((centurySeconds >> 8) & 0xFF),
                (byte) (centurySeconds & 0xFF)
        };
    }

    public static void main(String[] args) {
        // 将当前时间表示为世纪秒的字节数组
        byte[] currentCenturySeconds = getCurrentCenturySecondsBytes();
        System.out.println("Current century seconds: " + toHexString(currentCenturySeconds));
        // 将世纪秒的字节数组转换为时间
        Date date = fromCenturySecondsBytes(new byte[] {(byte)0x5E, (byte)0xAF, (byte)0x19, (byte)0x5E});
        System.out.println("Date from century seconds bytes: " + date);
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}