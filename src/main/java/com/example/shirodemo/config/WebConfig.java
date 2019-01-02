package com.example.shirodemo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.shirodemo.config.convert.DateConverter;
import com.example.shirodemo.config.convert.StringToBooleanConvert;
import com.example.shirodemo.config.convert.TrimStringConvert;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义配置.
 *
 * @author lixingwu
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 方法描述：消息转化器.
     * 创建时间：2018-12-06 21:09:41
     *
     * @author "lixingwu"
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1.需要先定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastConfig = new FastJsonConfig();
        fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //处理中文乱码问题(不然出现中文乱码)
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastConfig);
        //4.将convert添加到converts当中
        converters.add(fastConverter);
    }

    /**
     * 方法描述：配置类型转换器.
     * 创建时间：2018-12-06 21:09:41
     *
     * @author "lixingwu"
     */
    @PostConstruct
    public void initEditableAvlidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
        genericConversionService.addConverter(new TrimStringConvert());
        genericConversionService.addConverter(new DateConverter());
        genericConversionService.addConverter(new StringToBooleanConvert());
    }
}
