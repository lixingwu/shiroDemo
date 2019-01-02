package com.example.shirodemo.enums;

import lombok.Getter;

/**
 * 端口返回信息枚举
 * 状态码  <= 1999 为成功信息
 * 状态码  >= 2000 为失败信息
 *
 * @author lixingwu
 */
@Getter
public enum ResultEnum {
    /******* <=1999 为成功信息 ******/
    SUCCESS(1999, "成功"),
    /******* >=2000 为失败信息 ******/
    ERROR(2000, "失败"),;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
