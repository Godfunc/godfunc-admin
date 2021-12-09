package com.godfunc.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Godfunc
 * @date 2019/12/1 21:11
 */
@Data
@ApiModel("返回内容")
public class R<T> implements Serializable {

    @ApiModelProperty("响应码，0表示成功")
    private long code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("响应数据")
    private T data;

    private R(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private R(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private R(ApiCodeMsg apiCodeMsg) {
        this.code = apiCodeMsg.getCode();
        this.msg = apiCodeMsg.getMsg();
    }

    private R(ApiCodeMsg apiCodeMsg, T data) {
        this.code = apiCodeMsg.getCode();
        this.msg = apiCodeMsg.getMsg();
        this.data = data;
    }

    public static <T> R<T> restResult(long code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static R<String> restResult(ApiCodeMsg apiCodeMsg) {
        return new R<String>(apiCodeMsg.getCode(), apiCodeMsg.getMsg());
    }

    public static R<String> ok() {
        return new R<String>(ApiCodeMsg.SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(ApiCodeMsg.SUCCESS, data);
    }

    public static <T> R<T> ok(long code, T data) {
        return new R<T>(code, ApiMsg.SUCCESS, data);
    }

    public static R<String> fail() {
        return new R<String>(ApiCodeMsg.FAILED);
    }

    public static R<String> fail(ApiCodeMsg apiCodeMsg) {
        return new R<String>(apiCodeMsg.getCode(), apiCodeMsg.getMsg());
    }

    public static R<String> fail(String msg) {
        return new R<String>(ApiCode.FAIL, msg);
    }

    public static R<String> fail(long code, String msg) {
        return new R<String>(code, msg);
    }


}
