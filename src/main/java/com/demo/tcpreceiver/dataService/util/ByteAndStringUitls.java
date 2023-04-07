package com.demo.tcpreceiver.dataService.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class ByteAndStringUitls {

    /**
     * 字符串转化成16进制数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i += 2) {
            String byteString = str.substring(i, i+2);
            bytes[i / 2] = (byte) Integer.parseInt(byteString, 16);
        }
        return bytes;
    }

    public static String getFloatByString(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream =
                new DataInputStream(byteArrayInputStream);
        float v = dataInputStream.readFloat();

        // 将浮点数格式化为小数点后一位
        DecimalFormat df = new DecimalFormat("#.#");
        String result = df.format(v);
        return result;
    }
}
