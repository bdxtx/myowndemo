package com.example.csc.myapplication.bean;

/**
 * 作者：陈思村 on 2018/5/17.
 * 邮箱：chensicun@51ganjie.com
 */
public class JsonErrorBean {
    private boolean content;
    private Errors errors;
    private boolean hasError;

    public boolean isContent() {
        return content;
    }

    public void setContent(boolean content) {
        this.content = content;
    }

    public Errors getError() {
        return errors;
    }

    public void setError(Errors errors) {
        this.errors = errors;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
