package com.example.myweatherdraver.list_elements;

public class WeatherForRecicleDay {
    private String day;
    private String dayWeek;
    private int imgDay;
    private String temperatureDay;

    public WeatherForRecicleDay(String time, String dayWeek,  int img, String description) {
        this.day = time;
        this.dayWeek = dayWeek;
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

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }
}
