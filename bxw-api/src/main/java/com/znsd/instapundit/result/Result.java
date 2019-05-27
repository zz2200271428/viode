package com.znsd.instapundit.result;

import java.io.Serializable;

public class Result implements Serializable {

    private int code; //状态码
    private String msg; // 消息
    private Object data; // 数据

    private Result(CodeMsg cm) {
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    private Result(Object data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result success(CodeMsg cm) {
        return new Result(cm);
    }

    public static Result error(CodeMsg cm) {
        return new Result(cm);
    }

}
