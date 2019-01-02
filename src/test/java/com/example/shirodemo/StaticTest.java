package com.example.shirodemo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import org.junit.Test;

public class StaticTest {

    @Test
    public void read() {
        // 获取资源文件对象
        ClassPathResource resource = new ClassPathResource("/static/test.txt");
        // 根据文件路径，读取文件内容
        String string = FileUtil.readString(resource.getAbsolutePath(), "UTF-8");
        Console.log(string);
    }

}
