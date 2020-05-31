package com.example.myweatherdraver.list_elements;



import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class CityFavourites {
    private String city;
    private String temperature;
    private String time;
    private String dateText;
    private String dateAndTime;

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
