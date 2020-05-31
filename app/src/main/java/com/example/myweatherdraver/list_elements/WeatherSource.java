package com.example.myweatherdraver.list_elements;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;


import java.util.ArrayList;

public class WeatherSource {
    private ArrayList<WeatherForRecicle> listWeather;
    private Resources resources;


    public WeatherSource(Resources resources) {
        this.listWeather = new ArrayList<>();
        this.resources = resources; //чтобы вытащить данные из ресурсов

    }

    public WeatherSource build(){
        String[] descriptions = resources.getStringArray(R.array.items);
        int[] pictures = getImageArray();
        for (int i = 0; i < descriptions.length; i++) {
            listWeather.add(new WeatherForRecicle(pictures[i], descriptions[i]));
        }
        return this;
    }
    private int[] getImageArray(){
        TypedArray picters = resources.obtainTypedArray(R.array.pictures);
        int[] answer = new int[picters.length()];
        for (int i = 0; i < picters.length(); i++) {
            answer[i] = picters.getResourceId(i, 0);

        }
        return answer;
    }

    public ArrayList<WeatherForRecicle> getListWeather() {
        return listWeather;
    }
}
