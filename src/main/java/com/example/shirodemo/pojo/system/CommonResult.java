package com.example.shirodemo.pojo.system;

import com.example.shirodemo.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * The type Common result.
 *
 * @author lixingwu
 */
public class CommonResult {
    public static final int SUCCESS = 0;
    public static final int FAILS = 1;
    public static final int SERVER_ERROR = -1;
    private int code;
    private String msg;
    private Object data;

    public CommonResult() {
        this.code = FAILS;
        this.msg = "";
        this.data = null;
    }

    public CommonResult(boolean success, String msg) {
        this.code = success ? SUCCESS : FAILS;
        this.msg = msg;
        this.data = null;
    }

    public CommonResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = null;
    }

    public CommonResult(ResultEnum resultEnum, List<Object> objectList) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = objectList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == SUCCESS || (code >=1000 && code <=1999);
    }

    @JsonIgnore
    public boolean isFails() {
        return code == FAILS || code == SERVER_ERROR || (code >=2000 && code <= 2999);
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
