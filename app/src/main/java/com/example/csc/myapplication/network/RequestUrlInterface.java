package com.example.csc.myapplication.network;

import com.example.csc.myapplication.bean.JsonBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * 作者：陈思村 on 2018/5/25.
 * 邮箱：chensicun@51ganjie.com
 */
public interface RequestUrlInterface {
    String BASE_URL="https://dev.51ganjie.cn/";

    @POST("/app/animation/queryAnimation.do")
    Call<ResponseBody>getWelcome();
}
