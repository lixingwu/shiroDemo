package com.example.shirodemo.hutool;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.util.Date;

public class ConvertTest {

    @Test
    public void toStr() {
        int i = 1;
        System.out.println(Convert.toStr(i));

        Object[] obj = {"lixingwu", 10, new Date(), false, 1000000L};
        System.out.println(Convert.toStr(obj));

        Object o = null;
        System.out.println(Convert.toStr(o, "空"));
    }

    @Test
    public void toObjArray() {
        Integer[] i = {1, 2, 3, 4, 5};
        String[] is = Convert.toStrArray(i);
        System.out.println(is.getClass().toString());

        Long[] longs = {10L, 11L, 12L, 13L};
        Integer[] integers = Convert.toIntArray(longs);
        System.out.println(Convert.toStr(integers));

        double d = 15404.25;
        System.out.println(Convert.digitToChinese(d));

        String datatime = "2018-08-07 12:22:45";
        System.out.println(Convert.toDate(datatime));

        // Convert.toBigDecimal();
        // Convert.toBool();
        // Convert.toBooleanArray();
        // Convert.toByte();
        // Convert.toByteArray();
        // Convert.toChar();
        // Convert.toCharArray();
        // Convert.toDate();
        // Convert.toSBC();
        // Convert.toDBC();
        // Convert.toDouble();
        // Convert.toDoubleArray();
        // Convert.toEnum();
        // Convert.toFloat();
        // Convert.toFloatArray();
        // ...
    }
}
