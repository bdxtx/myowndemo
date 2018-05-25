package com.example.csc.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.csc.myapplication.MainActivity;
import com.example.csc.myapplication.R;
import com.example.csc.myapplication.adapter.MyViewPagerAdapter;
import com.example.csc.myapplication.base.BaseActivity;
import com.example.csc.myapplication.base.Constant;
import com.example.csc.myapplication.bean.JsonBean;
import com.example.csc.myapplication.network.HttpClient;
import com.example.csc.myapplication.network.MyCallBack;
import com.example.csc.myapplication.network.RequestUrlInterface;
import com.example.csc.myapplication.network.RequestUrlSign;
import com.example.csc.myapplication.utils.JsonParseUtil;
import com.wenming.library.save.imp.LogWriter;
import com.wenming.library.util.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WelcomeActivity extends BaseActivity implements MyCallBack.HttpCallBack {

    private ViewPager viewPager;
    private LayoutInflater inflater;
    private List<View> viewList;
    private MyViewPagerAdapter myViewPagerAdapter;
    private String TAG = Constant.WELCOME_TAG;
    private TextView jump;

    private CountDownTimer countDownTimer=new CountDownTimer(3000,500) {
        @Override
        public void onTick(long millisUntilFinished) {
            jump.setText("跳过（"+millisUntilFinished/1000+"）秒");
        }

        @Override
        public void onFinish() {
            gotoMainActivity();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewPager = (ViewPager) findViewById(R.id.vp_welcome);
        inflater = LayoutInflater.from(this);
        viewList = new ArrayList<>();
        myViewPagerAdapter = new MyViewPagerAdapter(viewList);
        viewPager.setAdapter(myViewPagerAdapter);

        if (getIsFirstFlag()){
            firstIn();
        }else {
            RequestUrlInterface requestUrlInterface= HttpClient.getRequestUrlInterface();
            Call<ResponseBody> call=requestUrlInterface.getWelcome();
            call.enqueue(new MyCallBack(RequestUrlSign.WELCOME,this));
            unFirstIn();
        }
    }
    //第一次进入App
    private void firstIn(){
        putFirstFlag();
        viewList.add(inflater.inflate(R.layout.intro_custom_layout1,null));
        viewList.add(inflater.inflate(R.layout.intro_custom_layout2,null));
        viewList.add(inflater.inflate(R.layout.intro_custom_layout3,null));
        viewList.add(inflater.inflate(R.layout.intro_custom_layout4,null));
        myViewPagerAdapter.notifyDataSetChanged();
        //初始化第四页的进入app的按钮
        TextView btn_navigation= viewList.get(3).findViewById(R.id.btn_navigation);
        btn_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogWriter.writeLog(TAG,"用户点击了进入app");
                gotoMainActivity();
            }
        });

    }
    //第二次进入app及以后
    private void unFirstIn(){
        viewList.add(inflater.inflate(R.layout.advertisement_welcome,null));
        myViewPagerAdapter.notifyDataSetChanged();
        jump = viewList.get(0).findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogWriter.writeLog(TAG,"用户点击了跳过");
                gotoMainActivity();
            }
        });
        countDownTimer.start();
    }
    private void putFirstFlag(){
        SharedPreferences sp=getSharedPreferences("info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isFirst",false);
        editor.apply();
    }
    private boolean getIsFirstFlag(){
        SharedPreferences sp=getSharedPreferences("info",Context.MODE_PRIVATE);
        return sp.getBoolean("isFirst",true);
    }
    private void gotoMainActivity(){
        Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(String sign, String result) {
        JsonBean jsonBean= JsonParseUtil.parseJson(result);
        if (RequestUrlSign.WELCOME.equals(sign)){

        }
    }

    @Override
    public void onFail(String sign, Call<ResponseBody> call, Throwable t) {

    }
}
