package com.example.myweatherdraver.list_elements;

import androidx.annotation.NonNull;

public class CityFavourites {
    private String city;
    private String temperature;

    public CityFavourites(String city, String temperature) {
        this.city = city;
        this.temperature = temperature;
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
}
