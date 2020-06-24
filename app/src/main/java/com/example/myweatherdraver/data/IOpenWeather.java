package com.example.myweatherdraver.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {

    @GET("forecast")
    Call<WeatherList> loadWeather(@Query("q") String cityCountry, @Query("units") String metric,
                                     @Query("lang") String lan, @Query("appid" ) String keyApi);

    @GET("forecast")
    Call<WeatherList> loadWeatherCoord(@Query("lat") String latitude, @Query("lon") String longitude, @Query("units") String metric,
                                          @Query("lang") String lan, @Query("appid") String keyApi);

}
