package com.example.myweatherdraver;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sPref;
    SharedPreferences.Editor editShare;
    private final String SPINNER_POSITION = "spinner_position";


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
