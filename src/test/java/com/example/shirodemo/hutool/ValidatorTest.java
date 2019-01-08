package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Validator;
import org.junit.Test;

public class ValidatorTest {
    @Test
    public void validator() {
        // 返回匹配结果
        Console.log(Validator.isLowerCase("sssss"));
        Console.log(Validator.isEmpty(""));

        // 匹配失败，抛出异常
        Validator.validateLowerCase("Sssss", "包含大写！");
        Validator.validateNotEmpty(" ", "内容为空！");

        // 自定义匹配
        Console.log(Validator.isMactchRegex("", ""));
        Validator.validateMatchRegex("a|b|c|d", "kkkk", "提示消息");
    }
}
