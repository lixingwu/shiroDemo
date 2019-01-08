package com.example.shirodemo.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import org.junit.Test;

public class DictTest {
    @Test
    public void dictT() {
        Dict dict = Dict.create()
                .set("int", 0)
                .set("String", "str")
                .set("long", 1000L)
                .set("date", DateUtil.now());
        Console.log(dict.getInt("int"));

        // 过滤出指定key
        Console.log(dict.filter("long", "int"));
    }
}
