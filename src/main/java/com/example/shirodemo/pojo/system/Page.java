package com.example.shirodemo.pojo.system;

import com.example.shirodemo.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * by 齐遇
 * The type Page.
 *
 * @param <T> the type parameter
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    private List<T> data = null;
    private String msg = "";
    private Long count = 0L;
    private Integer code = 0;

    /**
     * by 齐遇
     * Instantiates a new Page.
     *
     * @param resultEnum the result enum
     */
    public Page(ResultEnum resultEnum) {
        this.msg = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }

    /**
     * by 齐遇
     * Instantiates a new Page.
     * 成功
     *
     * @param data the data
     */
    public Page(List<T> data) {
        if (data instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) data;
            this.msg = ResultEnum.SUCCESS.getMessage();
            this.count = page.getTotal();
            this.data = page;
        }
    }
}
