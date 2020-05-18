package com.example.myweatherdraver.list_elements;

import androidx.annotation.NonNull;

public class CityFavourites {
    private String city;

    public CityFavourites(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @NonNull
    @Override
    public String toString() {
        return city;
    }
}
