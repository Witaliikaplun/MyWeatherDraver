package com.example.myweatherdraver.list_elements;

public class WeatherForRecicleDay {
    private String day;
    private int imgDay;
    private String temperatureDay;

    public WeatherForRecicleDay(String time, int img, String description) {
        this.day = time;
        this.imgDay = img;
        this.temperatureDay = description;
    }

    public int getImgDay() {
        return imgDay;
    }

    public String getTemperatureDay() {
        return temperatureDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
