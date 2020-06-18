package com.example.myweatherdraver.list_elements;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.myweatherdraver.R;
import com.example.myweatherdraver.data.DataParameters;

import java.util.ArrayList;

public class WeatherSourceDay {
    private ArrayList<WeatherForRecicleDay> listWeatherDay;
    private Resources resources;


    public WeatherSourceDay(Resources resources) {
        this.listWeatherDay = new ArrayList<>();
        this.resources = resources; //чтобы вытащить данные из ресурсов

    }

    public WeatherSourceDay build(){
        int[] pictures = getImageArray();
        for (int i = 0; i < 4; i++) {
            listWeatherDay.add(new WeatherForRecicleDay(DataParameters.getInstance().getDataListRequest().get(i).getDt_txt().substring(11, 16),
                    pictures[getIndexPictersDay(DataParameters.getInstance().getDataListRequest().get(i).getWeather()[0].getImg())],
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

    public int getIndexPictersDay(String responsIMG){
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

    public ArrayList<WeatherForRecicleDay> getListWeatherDay() {
        return listWeatherDay;
    }


}
