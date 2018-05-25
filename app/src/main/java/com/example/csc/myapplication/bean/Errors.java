package com.example.csc.myapplication.bean;

/**
 * 作者：陈思村 on 2018/5/17.
 * 邮箱：chensicun@51ganjie.com
 */
public class Errors {
    private int code;
    private String field;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
