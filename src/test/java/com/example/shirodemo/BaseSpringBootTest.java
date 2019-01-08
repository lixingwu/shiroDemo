package com.example.shirodemo;

import cn.hutool.core.date.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class BaseSpringBootTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Before
    public void setUp() throws Exception {
        this.setTime(System.currentTimeMillis());
        logger.info("==> 测试开始执行 <==");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("==> 测试执行完成，耗时：{} <==", DateUtil.formatBetween(System.currentTimeMillis() - this.getTime()));
    }

    /**
     * 方法描述：打印list.
     * 创建时间：2018-10-11 00:23:28
     */
    protected <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    protected void print(Object o) {
        System.out.println(o.toString());
    }

}
