package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Singleton;
import org.junit.Test;

public class SingletonTest {

    @Test
    public void print() {
        //单例，只会创建一个，无论get几个，只会第一个生效
        P p1 = Singleton.get(P.class, "hahaha");
        P p2 = Singleton.get(P.class, "花会受到");
        P p3 = Singleton.get(P.class, "申报表");
        p1.say();
        p2.say();
        p3.say();
        // -> 都输出 hahaha

        // 销毁单例
        Singleton.destroy();
        P p4 = Singleton.get(P.class, "申报表");
        p4.say();
    }

}

class P {
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    P(String str) {
        this.string = str;
    }

    public void say() {
        System.out.println(this.string);
    }
}