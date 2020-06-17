package com.example.myweatherdraver.list_elements;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.ImageView;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.data.DataParameters;
import com.squareup.picasso.Picasso;


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

        for (int i = 0; i < 8; i++) {
            listWeather.add(new WeatherForRecicle(pictures[getIndexPicters(DataParameters.getInstance().getDataListRequest().get(i).getWeather()[0].getImg())],
                    String.format("%.1f", DataParameters.getInstance().getDataListRequest().get(i).
                    getMain().getTemp())));

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

    public int getIndexPicters(String responsIMG){
        TypedArray picters = resources.obtainTypedArray(R.array.pictures);
        String namePicters = "";
        int index = 0;
        for (int i = 0; i < picters.length(); i++) {
            namePicters =  picters.getString(i).substring(18, 21);
            if(namePicters.equals(responsIMG)){
                index = i;
                break;
            }
        }
        return index;
    }

    public ArrayList<WeatherForRecicle> getListWeather() {
        return listWeather;
    }


}
