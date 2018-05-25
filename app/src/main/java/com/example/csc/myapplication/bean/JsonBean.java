package com.example.csc.myapplication.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 作者：陈思村 on 2018/5/9.
 * 邮箱：chensicun@51ganjie.com
 */
public class JsonBean {
    private int code;//返回码
    private String message;//返回提示信息
    private JSONObject data;//data数据,将JSONObject转成String只需要toString（）
    private String ext;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", ext='" + ext + '\'' +
                ", success=" + success +
                '}';
    }
}
