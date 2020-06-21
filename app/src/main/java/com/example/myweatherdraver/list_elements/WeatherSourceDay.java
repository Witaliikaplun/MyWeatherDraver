package com.example.myweatherdraver.list_elements;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

import com.example.myweatherdraver.R;
import com.example.myweatherdraver.data.DataParameters;
import com.example.myweatherdraver.data.WeatherRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeatherSourceDay {
    private ArrayList<WeatherForRecicleDay> listWeatherDay;
    private Resources resources;


    public WeatherSourceDay(Resources resources) {
        this.listWeatherDay = new ArrayList<>();
        this.resources = resources; //чтобы вытащить данные из ресурсов

    }

    public WeatherSourceDay build(){

        ArrayList<WeatherRequest> list = DataParameters.getInstance().getDataListRequest();

        Calendar calendar = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        DateFormat dateFormatTemp = new SimpleDateFormat("dd MMM");
        String actDate = dateFormat.format(calendar.getTime());

        Log.d("date", "date format: " + actDate);

        int[] pictures = getImageArray();
        for (int i = 0; i < list.size(); i++) {
            Calendar calendarTemp = new GregorianCalendar(Integer.parseInt(list.get(i).getDt_txt().substring(0, 4)), Integer.parseInt(list.get(i).getDt_txt().substring(5, 7)),
                    Integer.parseInt(list.get(i).getDt_txt().substring(8, 10)));
            String day_mon = dateFormatTemp.format(calendarTemp.getTime());

            if(!actDate.equals(list.get(i).getDt_txt().substring(0, 10)) && list.get(i).getDt_txt().substring(11, 16).equals("12:00")){
                listWeatherDay.add(new WeatherForRecicleDay(day_mon,
                        pictures[getIndexPictersDay(list.get(i).getWeather()[0].getImg())],
                        String.format("%.1f", list.get(i).
                                getMain().getTemp())));
            }


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
