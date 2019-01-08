package com.example.shirodemo.hutool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutoTest {

    private int i = 0;

    @Before
    public void before() {
        System.out.println("before：" + i++);
    }

    @After
    public void after() {
        System.out.println("after：" + i++);
    }

    @Test
    public void m1() {
        System.out.println("m1：" + i++);
    }

    @Test
    public void m2() {
        System.out.println("m2：" + i++);
    }

    @Test
    public void m3() {
        System.out.println("m3：" + i++);
    }

    @Test
    public void m4() {
        System.out.println("m4：" + i++);
    }
}
