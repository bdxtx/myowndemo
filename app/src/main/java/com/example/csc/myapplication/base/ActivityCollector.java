package com.example.csc.myapplication.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：陈思村 on 2018/5/10.
 * 邮箱：chensicun@51ganjie.com
 */
public class ActivityCollector {
    public static List<Activity>activityList=new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activityList) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
    public static void finishActivityByName(String activityName){
        for (Activity activity :activityList) {
            if (!activity.isFinishing()&&activity.getClass().getSimpleName().equals(activityName)){
                activity.finish();
            }
        }
    }
}
