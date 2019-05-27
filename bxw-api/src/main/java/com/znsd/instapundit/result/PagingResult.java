package com.znsd.instapundit.result;

import java.io.Serializable;

public class PagingResult implements Serializable {

    private int code;
    private String msg;
    private int count;
    private Object data;

    public PagingResult() {
    }

    private PagingResult(int count, Object data) {
        this.code = 0;
        this.msg = "成功";
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static PagingResult success(int count, Object data) {
        return new PagingResult(count, data);
    }

}
