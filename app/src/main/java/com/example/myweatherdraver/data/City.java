package com.example.myweatherdraver.data;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    private String nameCity;


    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String name) {
        this.nameCity = name;
    }
}
