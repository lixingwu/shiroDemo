package com.example.shirodemo.controller.system;

import com.example.shirodemo.enums.ResultEnum;
import com.example.shirodemo.pojo.system.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Controller 基类
 *
 * @author lixingwu
 */
@Controller
public class BaseCtl {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 方法描述：只带数据对象的CommonResult
     * 创建作者：李兴武
     * 创建日期：2018-08-15
     *
     * @param obj 数据对象
     * @return 只带数据对象的CommonResult
     */
    protected CommonResult resultDataWrapper(Object obj) {
        return resultSuccessWrapper(null, obj);
    }


    /**
     * 方法描述：自定义状态码的CommonResult
     * 创建作者：李兴武
     * 创建日期：2018-08-15
     *
     * @param code 自定义代码
     * @param msg  提示消息
     * @param obj  数据对象
     * @return CommonResult
     */
    protected CommonResult resultWrapper(int code, String msg, Object obj) {
        CommonResult result = new CommonResult();
        result.setMsg(msg);
        result.setData(obj);
        result.setCode(code);
        return result;
    }

    /**
     * @param code 自定义代码
     * @param msg  提示消息
     * @return CommonResult
     * @Author 齐遇
     * @Date 2018/10/10
     * 方法描述：自定义状态码的CommonResult
     */
    protected CommonResult resultWrapper(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    /**
     * @return CommonResult
     * @Author 齐遇
     * @Date 2018/10/10
     * 方法描述：自定义状态码的CommonResult
     */
    protected CommonResult resultWrapper(ResultEnum resultEnum) {
        CommonResult result = new CommonResult();
        result.setMsg(resultEnum.getMessage());
        result.setCode(resultEnum.getCode());
        return result;
    }

    /**
     * @return CommonResult
     * @Author 齐遇
     * @Date 2018/10/10
     * 方法描述：自定义状态码的CommonResult
     */
    protected CommonResult resultWrapper(ResultEnum resultEnum, Object object) {
        CommonResult result = new CommonResult();
        result.setMsg(resultEnum.getMessage());
        result.setCode(resultEnum.getCode());
        result.setData(object);
        return result;
    }


    /**
     * <p> 方法描述：bool 判断返回不同的枚举信息. </p>
     * <p> 创建时间：2018-10-15 18:20:11 </p>
     * <p> 创建作者：刘万琼 </p>
     *
     * @param bool        状态
     * @param successEnum the success enum
     * @param failsEnum   the fails enum
     * @param obj         数据对象
     * @return the common result
     */
    protected CommonResult resultWrapper(boolean bool, ResultEnum successEnum, ResultEnum failsEnum, Object obj) {
        return resultWrapper(bool ? successEnum : failsEnum, obj);
    }

    /**
     * <p> 方法描述：bool 判断返回不同的枚举信息. </p>
     * <p> 创建时间：2018-10-15 18:20:11 </p>
     * <p> 创建作者：刘万琼 </p>
     *
     * @param bool        状态
     * @param successEnum the success enum
     * @param failsEnum   the fails enum
     * @return the common result
     */
    protected CommonResult resultWrapper(boolean bool, ResultEnum successEnum, ResultEnum failsEnum) {
        return resultWrapper(bool ? successEnum : failsEnum);
    }

    /**
     * 方法描述：返回成功和失败的CommonResult
     * 创建作者：李兴武
     * 创建日期：2018-08-15
     *
     * @param bool       状态:true成功,false失败
     * @param successMsg true成功 提示消息
     * @param failsMsg   false失败 提示消息
     * @param obj        需要传递的数据对象
     * @return CommonResult对象
     */
    protected CommonResult resultBoolWrapper(boolean bool, String successMsg, String failsMsg, Object obj) {
        if (bool) {
            return resultSuccessWrapper(successMsg, obj);
        }
        return resultFailsWrapper(failsMsg, obj);
    }

    /**
     * 方法描述：返回成功的CommonResult
     * 创建作者：李兴武
     * 创建日期：2018-08-15
     *
     * @param msg 成功提示的消息
     * @param obj 需要传递的数据对象
     * @return CommonResul对象
     */
    protected CommonResult resultSuccessWrapper(String msg, Object obj) {
        return resultWrapper(CommonResult.SUCCESS, msg, obj);
    }

    /**
     * 方法描述：返回失败的CommonResult
     * 创建作者：李兴武
     * 创建日期：2018-08-15
     *
     * @param msg 失败提示的消息
     * @param obj 需要传递的数据对象
     * @return CommonResul对象
     */
    protected CommonResult resultFailsWrapper(String msg, Object obj) {
        return resultWrapper(CommonResult.FAILS, msg, obj);
    }

    /**
     * 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,
     * 动态获取可以兼容由于收银宝加字段而引起的签名异常
     */
    protected TreeMap<String, String> getParams(HttpServletRequest request) {
        TreeMap<String, String> map = new TreeMap<>();
        Map reqMap = request.getParameterMap();
        for (Object key : reqMap.keySet()) {
            String value = ((String[]) reqMap.get(key))[0];
            map.put(key.toString(), value);
        }
        return map;
    }

}
