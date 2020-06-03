package com.example.myweatherdraver.data;

import android.util.Log;

import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.ui.home.FragmentHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRetrofit {
    private IOpenWeather iOpenWeather;
    FragmentHome fh;
    private String city;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String description;
    private String img;

    public RequestRetrofit(IOpenWeather iOpenWeather, FragmentHome fh) {
        this.iOpenWeather = iOpenWeather;
        this.fh = fh;
    }

    public void request(){
        iOpenWeather.loadWeather(city, "metric", "ru", BuildConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

                if (response.body() != null && response.isSuccessful()) {
                    temperature = String.format("%.1f", response.body().getMain().getTemp());
                    pressure = String.format("%d", response.body().getMain().getPressure());
                    humidity = String.format("%d", response.body().getMain().getHumidity());
                    windSpeed = String.format("%d", response.body().getWind().getSpeed());
                    description = String.format("%s", response.body().getWeather()[0].getDescription());
                    img = response.body().getWeather()[0].getImg();
                    fh.requestAndUpdate();
                }
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                Log.d("img", "error");
            }
        });
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
