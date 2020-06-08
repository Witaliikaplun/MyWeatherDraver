package com.example.myweatherdraver.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
    @GET("weather")
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("units") String metric,
                                     @Query("lang") String lan, @Query("appid" ) String keyApi);

    @GET("weather")
    Call<WeatherRequest> loadWeatherCoord(@Query("lat") String latitude, @Query("lon") String longitude, @Query("units") String metric,
                                          @Query("lang") String lan, @Query("appid") String keyApi);



//https://api.openweathermap.org/data/2.5/weather?lat=45.04&lon=38.97&units=metric&appid=...
}
