package com.example.shirodemo.config.component;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取application.yml文件的custom下的属性
 *
 * @author lixingwu
 */
@Component
@ConfigurationProperties(prefix = "custom")
@Data
@ToString
public class ConfigProperty {
    private String sysVersion;
    private String sysName;
    private String createTime;
    private String copyright;
}
