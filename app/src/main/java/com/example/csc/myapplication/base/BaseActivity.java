package com.example.csc.myapplication.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wenming.library.save.imp.LogWriter;

public class BaseActivity extends AppCompatActivity {
    private String TAG=Constant.BASE_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        LogWriter.writeLog(TAG, getClass().getSimpleName()+" onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        LogWriter.writeLog(TAG, getClass().getSimpleName()+" onDestroy");
    }
}
