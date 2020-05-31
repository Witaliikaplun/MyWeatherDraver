package com.example.myweatherdraver.data;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("icon")
    private String img;

    private String description;

    public Weather(String img, String description) {
        this.img = img;
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }
}
