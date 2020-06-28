package com.example.myweatherdraver.reciver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import androidx.core.app.NotificationCompat;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;

public class WiFiReciver extends BroadcastReceiver {
    public static final String TITLE_WIFI_RECIVER = "Wi-Fi Receiver";
    private int messageId = 20;
    @Override
    public void onReceive(Context context, Intent intent) {
        int extra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);

            if(extra == WifiManager.WIFI_STATE_DISABLED) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(TITLE_WIFI_RECIVER)
                        .setContentText(context.getString(R.string.no_connect_wifi));
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(messageId++, builder.build());
            }
    }
}