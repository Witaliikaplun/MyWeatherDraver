package com.example.myweatherdraver.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myweatherdraver.list_elements.CityFavourites;

@Database(entities = {CityFavourites.class}, version = 1)
public abstract class CityDatabase extends RoomDatabase {
    public abstract ICityFavDAO getiCityFavDAO();
}
