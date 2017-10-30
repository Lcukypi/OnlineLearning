package com.guanglian.onlinelearning3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.jpush.android.api.JPushInterface;


public class StartService extends Service {
    public IBinder onBind(Intent intent){
        return null;
    }
    public void onCreate(){
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setLatestNotificationNumber(getApplicationContext(),10);
    }
}
