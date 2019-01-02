package com.example.shirodemo.config.exception;

import com.example.shirodemo.pojo.system.CommonResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理器
 *
 * @author lixingwu
 */
@ControllerAdvice
public class HandleException {

    /**
     * 参数异常拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResult missingParamterHandler(MissingServletRequestParameterException e) {
        String paramsName = e.getParameterName();
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(CommonResult.SERVER_ERROR);
        commonResult.setMsg(String.format("参数不存在：%s", paramsName));
        e.printStackTrace();
        return commonResult;
    }

    /**
     * 异常信息转化为CommonResult对象，格式化输出
     *
     * @param e 移除对象
     * @return commonResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult handleException(Exception e) {
        CommonResult commonResult = new CommonResult();
        // 自定义异常，使用自定义code
        if (e instanceof FailException) {
            commonResult.setCode(((FailException) e).getCode());
            commonResult.setData(((FailException) e).getData());
        } else {
            commonResult.setCode(CommonResult.SERVER_ERROR);
        }
        commonResult.setMsg(e.getMessage());
        e.printStackTrace();
        return commonResult;
    }
}
