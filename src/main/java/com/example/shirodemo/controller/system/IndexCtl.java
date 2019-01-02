package com.example.shirodemo.controller.system;

import com.example.shirodemo.config.component.ConfigProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统默认配置控制层
 *
 * @author lixingwu
 */
@RestController
@Api(description = "系统默认配置控制层", tags = {"IndexCtl"})
public class IndexCtl extends BaseCtl {

    private final ConfigProperty property;

    @Autowired
    public IndexCtl(ConfigProperty property) {
        this.property = property;
    }

    /**
     * 方法描述：系统首页.
     * 创建时间：2018-12-06 21:13:37
     *
     * @author "lixingwu"
     */
    @ApiOperation(value = "系统首页", notes = "返回“OK，配置成功！系统版本{}”")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "OK，配置成功！系统版本：" + property.getSysVersion();
    }

    /**
     * 方法描述：JSON返回.
     * 创建时间：2018-12-06 21:13:37
     *
     * @author "lixingwu"
     */
    @ApiOperation(value = "JSON返回", notes = "测试返回json数据，返回系统的信息")
    @RequestMapping(value = "/sysInfo", method = RequestMethod.GET)
    public Map sysInfo() {
        HashMap<String, Object> obj = new HashMap<>(5);
        obj.put("sysName", property.getSysName());
        obj.put("sysVersion", property.getSysVersion());
        obj.put("createTime", property.getCreateTime());
        obj.put("copyright", property.getCopyright());
        return obj;
    }
}
