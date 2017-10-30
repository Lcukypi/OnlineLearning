package com.guanglian.onlinelearning3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BootCompletedReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            Log.d("yqb","检测到开机服务，去启动服务");
            Intent newIntent = new Intent(context,StartService.class);
            context.startService(newIntent);
        }
    }
}
