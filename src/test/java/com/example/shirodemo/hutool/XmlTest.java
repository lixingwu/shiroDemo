package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.junit.Test;

public class XmlTest {

    @Test
    public void readXML() {
        String xmlStr = "<returnsms><returnstatus>Success（成功）</returnstatus><message>ok</message><remainpoint>1490</remainpoint><taskID>885</taskID><successCounts>1</successCounts></returnsms>";
        JSONObject object = XML.toJSONObject(xmlStr);
        Console.log(object);
    }
}
