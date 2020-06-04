package com.example.myweatherdraver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myweatherdraver.reciver.BatteryReciver;
import com.example.myweatherdraver.reciver.WiFiReciver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String TOKEN = "TOKEN";
    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sPref;
    SharedPreferences.Editor editShare;
    private final String SPINNER_POSITION = "spinner_position";

    public static final String CHANNEL_ID = "2";
    public static final String CHANNEL_NAME = "name";
    private BroadcastReceiver airplanReceiver;
    WiFiReciver wiFiReciver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        initSettings();

        if (Singleton.getSingleton().getSwitchTheme()) setTheme(R.style.AppDarkTheme);
        else setTheme(R.style.AppTheme);

        setSupportActionBar(toolbar);
        initDrawer();

        initChannel();

        airplanReceiver = new BatteryReciver();
        wiFiReciver = new WiFiReciver();

        registerReceiver(airplanReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wiFiReciver, filter);

        initGetToken();
        initNotificationChannel();
    }

    private void initGetToken() {

        FirebaseInstanceId. getInstance ().getInstanceId()
                .addOnCompleteListener( new OnCompleteListener<InstanceIdResult>()
                {
                    @Override
                    public void onComplete( @NonNull Task<InstanceIdResult> task)
                    {
                        if (!task.isSuccessful()) {
                            Log.w ( "PushMessage" , "getInstanceId failed" ,
                                    task.getException());
                            return ;
                        }
// Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d(TOKEN, token);
                    }
                });
    }

    private void initNotificationChannel() {
        if (Build.VERSION. SDK_INT >= Build.VERSION_CODES. O ) {
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context. NOTIFICATION_SERVICE );
            int importance = NotificationManager. IMPORTANCE_LOW ;
            NotificationChannel channel = new NotificationChannel( "2" , "name" ,
                    importance);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initSettings() {
        sPref = getPreferences(MODE_PRIVATE);
        editShare = sPref.edit();
        Singleton.getSingleton().setPositionSpinner(sPref.getInt(SPINNER_POSITION, 0));
        Toast.makeText(this, Integer.valueOf(Singleton.getSingleton().getPositionSpinner()).toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //сохраняем выбранный город в Spinner
        editShare.putInt(SPINNER_POSITION, Singleton.getSingleton().getPositionSpinner());
        editShare.apply();
        Toast.makeText(this, Integer.valueOf(Singleton.getSingleton().getPositionSpinner()).toString(),Toast.LENGTH_SHORT).show();

        unregisterReceiver(airplanReceiver);
        unregisterReceiver(wiFiReciver);
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
