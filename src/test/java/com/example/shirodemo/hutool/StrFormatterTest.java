package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.text.StrFormatter;
import org.junit.Test;


public class StrFormatterTest {
    @Test
    public void strFormatter() {
        String s = "大家好！我是{}，欢迎来到{}。";
        Console.log(StrFormatter.format(s, " 李兴武 ", " 看得我 "));

        //转义
        String s2 = "大家好！我是\\{}，欢迎来到{}。";
        Console.log(StrFormatter.format(s2, " 看得我 "));
    }
}
