package com.example.myweatherdraver.list_elements;

import com.google.gson.annotations.SerializedName;

public class WeatherForRecicle {
    private String time;
    private int img;
    private String description;

    public WeatherForRecicle(String time, int img, String description) {
        this.time = time;
        this.img = img;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
