package com.example.csc.myapplication.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.csc.myapplication.bean.Errors;
import com.example.csc.myapplication.bean.JsonBean;
import com.example.csc.myapplication.bean.JsonErrorBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：陈思村 on 2018/5/9.
 * 邮箱：chensicun@51ganjie.com
 */
public class JsonParseUtil {
    public static JsonBean parseJson(String result){
        JsonBean jsonBean=new JsonBean();
        try{
            JSONObject jsonObject = JSON.parseObject(result);
            jsonBean.setCode(jsonObject.getInteger("code"));
            jsonBean.setMessage(jsonObject.getString("message"));
            jsonBean.setData(jsonObject.getJSONObject("data"));
            jsonBean.setExt(jsonObject.getString("ext"));
            jsonBean.setSuccess(jsonObject.getBoolean("success"));
        }catch (Exception e){
            Log.d("JsonParseUtil",e.toString());
        }
        return jsonBean;
    }
    public static JsonErrorBean parseErrorJson(String result){
        JsonErrorBean jsonErrorBean = new JsonErrorBean();
        try{
            JSONObject jsonObject = JSON.parseObject(result);
            jsonErrorBean.setContent(jsonObject.getBoolean("content"));
            String errorsJson=jsonObject.getString("errors");
            Gson gson=new Gson();
            List<Errors> errors=gson.fromJson(errorsJson,new TypeToken<ArrayList<Errors>>(){}.getType());
            jsonErrorBean.setError(errors.get(0));
            jsonErrorBean.setHasError(jsonObject.getBoolean("hasError"));
        }catch (Exception e){
            Log.d("JsonParseUtil",e.toString());
        }
        return jsonErrorBean;
    }
}
