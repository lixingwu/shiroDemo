package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

public class StrUtilTest {

    @Test
    public void toCamelCaseTest() {
        // 动态拼接方法名，例如：pic_sysphoto事件的对应方法名 wxPicSysphoto
        String methodName = "wx" + StrUtil.upperFirst(StrUtil.toCamelCase("pic_sysphoto"));
        Console.log(methodName);
    }
}
