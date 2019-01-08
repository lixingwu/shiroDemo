package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class IdUtilTest {
    @Test
    public void UUID() {
        Console.log(IdUtil.simpleUUID());
        Console.log(IdUtil.randomUUID());
    }

    @Test
    public void ObjectId() {
        Set set = new HashSet();
        for (int i = 0; i < 100000; i++) {
            set.add(ObjectId.next());
        }
        Console.log(set.size());
    }

    @Test
    public void Snowflake() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Set set = new HashSet();
        for (int i = 0; i < 100000; i++) {
            set.add(snowflake.nextId());
        }
        Console.log(set.size());
    }


}
