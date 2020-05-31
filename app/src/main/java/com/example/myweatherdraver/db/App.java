package com.example.myweatherdraver.db;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static App instance;

    //База данных
    CityDatabase cityDB;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Сохраняем объект приложения (для Singleton’а)
        instance = this;
        //Строим базу данных
        cityDB = Room.databaseBuilder(getApplicationContext(), CityDatabase.class, "cityfavourites")
                .allowMainThreadQueries()
                .build();
    }
    public ICityFavDAO getICityFavDAO(){
        return cityDB.getiCityFavDAO();
    }
}
