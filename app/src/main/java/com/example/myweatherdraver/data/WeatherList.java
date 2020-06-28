package com.example.myweatherdraver.data;

import java.util.ArrayList;

public class WeatherList {
    private ArrayList<WeatherRequest> list = new ArrayList<>();
    private City city;


    public ArrayList<WeatherRequest> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherRequest> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
