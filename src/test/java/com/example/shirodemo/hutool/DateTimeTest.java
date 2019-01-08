package com.example.shirodemo.hutool;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {

    @Test
    public void dateLongCalendar() {
        System.out.println("Date：" + DateUtil.date());
        System.out.println("Calendar：" + DateUtil.date(Calendar.getInstance()));
        System.out.println("Long：" + DateUtil.date(System.currentTimeMillis()));
        System.out.println("now：" + DateUtil.now());
        System.out.println("date：" + DateUtil.today());
    }

    @Test
    public void parseFormat() {
        Date date = DateUtil.parse("2017-01-01");
        Date date2 = DateUtil.parse("2017-01-01", "yyyy-MM-dd");
        System.out.println(date);
        System.out.println(date2);

        Date date3 = new Date();
        System.out.println(DateUtil.formatDate(date3));
        System.out.println(DateUtil.formatTime(date3));
        System.out.println(DateUtil.formatDateTime(date3));
        System.out.println(DateUtil.format(date3, "yyyy-MM-dd"));
    }
}
