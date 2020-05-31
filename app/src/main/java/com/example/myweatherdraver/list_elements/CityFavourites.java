package com.example.myweatherdraver.list_elements;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

// @Entity - это признак табличного объекта, то есть объект будет сохраняться
// в базе данных в виде строки
// indices указывает на индексы в таблице
@Entity(indices = {@Index(value = {"Date_Time", "City", "Temperature"})})
public class CityFavourites {
    @ColumnInfo(name = "City")
    public String city;

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "Temperature")
    public String temperature;

    @Ignore
    private String time;

    @Ignore
    private String dateText;

    @ColumnInfo(name = "Date_Time")
    public String dateAndTime;

    public CityFavourites(String city, String temperature) {
        this.city = city;
        this.temperature = temperature;

        // Текущее время
        Date currentDate = new Date();
// Форматирование времени как "день.месяц.год"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM", Locale.getDefault());
        dateText = dateFormat.format(currentDate);
// Форматирование времени как "часы:минуты:секунды"
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        time = timeFormat.format(currentDate);
        dateAndTime = dateText + " " + time;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    @NonNull
    @Override
    public String toString() {
        return city;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
}
