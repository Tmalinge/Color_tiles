package com.example.color_tiles;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class MyTimeService extends Service {

    int time=1;

    public class MyBinder extends Binder{
        MyTimeService getService(){
            return MyTimeService.this;
        }
    }

    private final MyBinder myBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            System.out.println("Handler");
            Intent intent1 = new Intent(ActivityGame.BROADCAST);
            intent1.putExtra("time", time);
            sendBroadcast(intent1);
        }, 1000);
        return START_STICKY;
    }

}