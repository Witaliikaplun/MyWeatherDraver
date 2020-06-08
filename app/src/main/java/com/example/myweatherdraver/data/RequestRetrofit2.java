package com.example.myweatherdraver.data;

import android.util.Log;

import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.ui.home.FragmentHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRetrofit2 {

    private IOpenWeather2 iOpenWeather2;
    FragmentHome fh;

    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String description;
    private String img;
    private String lat;
    private String lon;



    public RequestRetrofit2(IOpenWeather2 iOpenWeather2, FragmentHome fh) {
        this.iOpenWeather2 = iOpenWeather2;
        this.fh = fh;
    }



    public void request2(){
        iOpenWeather2.loadWeatherCoord("45.04", "38.97", "metric", "ru", BuildConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

                if (response.body() != null && response.isSuccessful()) {
                    temperature = String.format("%.1f", response.body().getMain().getTemp());
                    pressure = String.format("%d", response.body().getMain().getPressure());
                    humidity = String.format("%d", response.body().getMain().getHumidity());
                    windSpeed = String.format("%d", response.body().getWind().getSpeed());
                    description = String.format("%s", response.body().getWeather()[0].getDescription());
                    img = response.body().getWeather()[0].getImg();
                    //fh.requestAndUpdate();
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




    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
