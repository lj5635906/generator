package com.git.generator.controller.vo;
import lombok.Data;

/**
 * 所有Http请求响应基类
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-03-20 10:18
 **/
@Data
public class ResultVo<T> {

    private ResultVo() {
    }

    private Integer code;
    private String message;
    private T data;

    /**
     * 请求成功
     *
     * @return ResultVo
     */
    public static ResultVo ok() {
        ResultVo result = new ResultVo();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 请求失败
     *
     * @return ResultVo
     */
    public static ResultVo fail() {
        ResultVo result = new ResultVo();
        result.setCode(500);
        result.setMessage("fail");
        return result;
    }

    /**
     * 请求成功
     *
     * @param data Object
     * @return ResultVo
     */
    public static ResultVo ok(Object data) {
        ResultVo result = new ResultVo();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

}
