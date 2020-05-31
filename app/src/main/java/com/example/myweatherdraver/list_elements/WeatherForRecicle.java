package com.example.myweatherdraver.list_elements;

import com.google.gson.annotations.SerializedName;

public class WeatherForRecicle {
    private int img;

    private String description;

    public WeatherForRecicle(int img, String description) {
        this.img = img;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }
}
