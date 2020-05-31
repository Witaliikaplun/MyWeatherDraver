package com.example.myweatherdraver.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myweatherdraver.list_elements.CityFavourites;

import java.util.List;
@Dao
public interface ICityFavDAO {
    //@Insert(onConflict = OnConflictStrategy.REPLACE )
    @Insert
    void insertCityFav(CityFavourites cityFav);

    @Delete
    void deleteCityFav(CityFavourites cityFav);

    @Query("SELECT * FROM cityfavourites")
    List<CityFavourites> getAllCityFav();

}
