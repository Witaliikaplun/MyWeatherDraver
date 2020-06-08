package com.example.myweatherdraver.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private Retrofit mRetrofit;
    private Retrofit mRetrofit2;
    private IOpenWeather iOpenWeather;
    private IOpenWeather2 iOpenWeather2;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()  //настройка ретрофита
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofit2 = new Retrofit.Builder()  //настройка ретрофита
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iOpenWeather = mRetrofit.create(IOpenWeather.class); //Создаем объект, при помощи
                                                        // которого будем выполнять запросы

        iOpenWeather2 = mRetrofit2.create(IOpenWeather2.class); //Создаем объект, при помощи
        // которого будем выполнять запросы2
    }

    public static NetworkService getInstance(){
        if(mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public IOpenWeather getiOpenWeather() {
        return iOpenWeather;
    }

    public IOpenWeather2 getiOpenWeather2() {
        return iOpenWeather2;
    }
}
