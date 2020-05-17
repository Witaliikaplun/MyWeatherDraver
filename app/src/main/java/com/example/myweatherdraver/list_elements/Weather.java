package com.example.myweatherdraver.list_elements;

public class Weather {
    private int img;
    private String description;

    public Weather(int img, String description) {
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
