package com.example.my_push_message;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.myweatherdraver.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessService extends FirebaseMessagingService {
    private int messageId = 0;

    public MyFirebaseMessService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d ( "PushMessage" , remoteMessage.getNotification().getBody());
        String title = remoteMessage.getNotification().getTitle();
        if (title == null ){
            title = "Push Message" ;
        }
        String text = remoteMessage.getNotification().getBody();
// создать нотификацию
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this ,
                "2" )
                .setSmallIcon(R.mipmap. ic_launcher )
                .setContentTitle(title)
                .setContentText(text);
        NotificationManager notificationManager =
                (NotificationManager)
                        this .getSystemService(Context. NOTIFICATION_SERVICE );
        notificationManager.notify( messageId ++, builder.build());
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Log. d ( "PushMessage" , "Token " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
    }
}
