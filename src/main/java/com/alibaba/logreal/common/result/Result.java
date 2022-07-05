package com.alibaba.logreal.common.result;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import lombok.Data;
import org.springframework.http.HttpMessage;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(HttpCodeEnum httpCodeEnum) {
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMessage();
    }

    public Result() {
    }

    public Result<T> buildMessage(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result<T> buildData(T obj) {
        this.setData(obj);
        return this;
    }

}
