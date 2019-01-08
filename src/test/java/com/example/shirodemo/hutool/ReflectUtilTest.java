package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReflectUtil;
import com.example.shirodemo.BaseSpringBootTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射测试.
 */
public class ReflectUtilTest extends BaseSpringBootTest {

    @Test
    public void weChatServiceTest() {
        Method[] methods = ReflectUtil.getMethods(this.getClass());
        Arrays.stream(methods).forEach(System.out::println);
    }

    @Test
    public void methodTest() throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectUtil.getMethod(this.getClass(), "toString");
        Console.log(method);
    }

    @Test
    public void methodTest2() throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectUtil.getMethod(this.getClass(), "methodTest3");
        Console.log(method);
    }

    @Test
    public void invokeTest() {
        Object obj = ReflectUtil.invoke(this, "redisGetAccessToken");
        Console.log(obj);
    }

}
