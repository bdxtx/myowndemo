package com.example.csc.myapplication.network;

import com.example.csc.myapplication.bean.JsonBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：陈思村 on 2018/5/17.
 * 邮箱：chensicun@51ganjie.com
 */
public class HttpClient {
    private static Retrofit retrofit=null;

    public static RequestUrlInterface getRequestUrlInterface() {
        Retrofit retrofit=getRetrofitClient();
        return retrofit.create(RequestUrlInterface.class);
    }

    private static Retrofit getRetrofitClient(){
        if (retrofit==null){
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            //设置超时时间
            setTimeOutConfig(builder);
            OkHttpClient okHttpClient=builder.build();
            retrofit=new Retrofit.Builder()
                    .baseUrl(RequestUrlInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
    private static void setTimeOutConfig(OkHttpClient.Builder builder){
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(50,TimeUnit.SECONDS);
        builder.writeTimeout(50,TimeUnit.SECONDS);
    }
}
