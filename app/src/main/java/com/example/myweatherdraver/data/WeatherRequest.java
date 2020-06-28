package com.example.myweatherdraver.data;

import com.example.myweatherdraver.list_elements.WeatherForRecicle;
import com.google.gson.annotations.SerializedName;


public class WeatherRequest {
    private Weather[] weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;

    private String dt_txt;

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }


}
