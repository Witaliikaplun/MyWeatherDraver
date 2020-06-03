package com.example.myweatherdraver.reciver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import androidx.core.app.NotificationCompat;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;

public class BatteryReciver extends BroadcastReceiver {
    public static final String TITLE_BAT_RECIVER = "Battery Receiver";
    public static final int SET_POINT_LOW_BAT = 5;
    private int messageId = 10;
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        String bateria = String.valueOf(level);
        if(level < SET_POINT_LOW_BAT){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(TITLE_BAT_RECIVER)
                    .setContentText("Низкий уровень заряда батареи");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(messageId, builder.build());
        }


    }
}
