package com.example.csc.myapplication.network;

import android.util.Log;

import com.wenming.library.save.imp.LogWriter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：陈思村 on 2018/5/25.
 * 邮箱：chensicun@51ganjie.com
 */
public class MyCallBack implements Callback<ResponseBody> {
    private HttpCallBack httpCallBack;
    private String sign;
    public MyCallBack(String sign,HttpCallBack httpCallBack){
        this.httpCallBack=httpCallBack;
        this.sign=sign;
    }
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            String result=response.body().string();
            LogWriter.writeLog("response",result);
            httpCallBack.onSuccess(sign,result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        httpCallBack.onFail(sign,call,t);
    }
    public interface HttpCallBack{
        void onSuccess(String sign,String result);
        void onFail(String sign,Call<ResponseBody> call, Throwable t);
    }
}
