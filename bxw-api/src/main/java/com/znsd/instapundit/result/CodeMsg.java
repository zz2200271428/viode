package com.znsd.instapundit.result;

import java.io.Serializable;

public class CodeMsg implements Serializable {

    private int code;
    private String msg;

    // 通用成功码
    public static CodeMsg SUCCESS = new CodeMsg(200, "成功");
    // 通用失败码
    public static CodeMsg ERROR = new CodeMsg(500, "服务器错误");
    // SQL错误
    public static CodeMsg SQL_ERROR = new CodeMsg(500, "SQL错误");

    // 页面引擎模板错误
    public static CodeMsg PAGE_ERROR = new CodeMsg(500, "页面引擎模板错误");

    // 页面引擎模板错误
    public static CodeMsg POINE_ERROR = new CodeMsg(500, "服务器空值错误");

    // 未知错误
    public static CodeMsg UNKNOWN_ERROR = new CodeMsg(500, "未知错误");


    // JSR303参数校验错误
    public static CodeMsg PARAM_ERROR = new CodeMsg(400, "参数异常");
    //增加成功
    public static CodeMsg ADD_SUCCESS = new CodeMsg(200, "增加成功");
    //修改成功
    public static CodeMsg UPDATE_SUCCESS = new CodeMsg(200, "修改成功");
    //修改失败
    public static CodeMsg UPDATE_ERROR = new CodeMsg(400, "修改失败");
    //增加失败
    public static CodeMsg ADD_ERROR = new CodeMsg(400, "增加失败");
    //删除成功
    public static CodeMsg DELETE_SUCCESS = new CodeMsg(200, "删除成功");
    //删除失败
    public static CodeMsg DELETE_ERROR = new CodeMsg(400, "删除失败");


    /**
     * 登录模块异常
     * 5001XX
     */
    public static CodeMsg LOGIN_ERROR = new CodeMsg(400, "密码或用户名错误！"); // 登录失败
    public static CodeMsg LOGIN_SUCCESS = new CodeMsg(200, "登录成功！"); // 登录失败
    public static CodeMsg QX_ERROR = new CodeMsg(500101, "没有权限"); // 没有权限

    /**
     * 提问模块
     *
     * @param code
     * @param msg
     */
    public static CodeMsg ISSUE_ERROR = new CodeMsg(400, "提问失败！");
    public static CodeMsg ISSUE_SUCCESS = new CodeMsg(200, "提问成功！");

    /**
     * @param code
     * @param msg
     */
    public static CodeMsg COMMENT_ERROR = new CodeMsg(400, "发表失败");
    public static CodeMsg COMMENT_SUCCESS = new CodeMsg(200, "发表成功");

    public static CodeMsg COLLECT_ERROR = new CodeMsg(400, "收藏错误");
    public static CodeMsg COLLECT_SUCCESS = new CodeMsg(200, "收藏成功");

    public static CodeMsg PAY_ERROR = new CodeMsg(400, "购买失败");
    public static CodeMsg MONEY_NOT_SUFFICIENT = new CodeMsg(400500, "余额不足");
    public static CodeMsg PAY_SUCCESS = new CodeMsg(200, "购买成功");

    public static CodeMsg PAYVIP_ERROR = new CodeMsg(400, "会员开通失败,余额不足！"); // 开通失败
    public static CodeMsg PAYVIP_SUCCESS = new CodeMsg(200, "会员开通成功！"); // 开通成功
    public static CodeMsg CHARGE_ERROR = new CodeMsg(400, "余额充值失败！"); // 充值失败
    public static CodeMsg CHARGE_SUCCESS = new CodeMsg(200, "余额充值成功！"); // 充值成功
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
