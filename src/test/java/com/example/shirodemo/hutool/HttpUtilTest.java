package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpUtilTest {

    @Test
    public void dianhuaApi() {
        String tel = "152";
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("tel", tel);
        String res = HttpUtil.get("http://mobsec-dianhua.baidu.com/dianhua_api/open/location", paramMap);
        JSONObject object = JSONUtil.parseObj(res).getJSONObject("response").getJSONObject(tel);
        String location = object.getStr("location");
        Console.log(location);
    }
}
