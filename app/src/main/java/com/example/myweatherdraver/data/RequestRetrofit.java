package com.example.myweatherdraver.data;

import android.util.Log;

import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.db.App;
import com.example.myweatherdraver.ui.home.FragmentHome;

import java.util.ArrayList;

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
    private String name;
    private String dt_txt;
    private String lat;
    private String lon;

    public RequestRetrofit(IOpenWeather iOpenWeather, FragmentHome fh) {
        this.iOpenWeather = iOpenWeather;
        this.fh = fh;
    }

    public void request(){
        iOpenWeather.loadWeather(city, "metric", "ru", BuildConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {

                if (response.body() != null && response.isSuccessful()) {
                    temperature = String.format("%.1f", response.body().getList().get(0).getMain().getTemp());
                    pressure = String.format("%d", response.body().getList().get(0).getMain().getPressure());
                    humidity = String.format("%d", response.body().getList().get(0).getMain().getHumidity());
                    windSpeed = String.format("%.1f", response.body().getList().get(0).getWind().getSpeed());
                    description = String.format("%s", response.body().getList().get(0).getWeather()[0].getDescription());
                    img = response.body().getList().get(0).getWeather()[0].getImg();
                    name = String.format("%s", response.body().getCity().getNameCity());
                    dt_txt = response.body().getList().get(0).getDt_txt();
                    DataParameters.getInstance().getDataListRequest().addAll(response.body().getList());


                    setActualParam();
                    fh.initRecycleWeather();
                    fh.requestAndUpdate();
                    if(!Singleton.getSingleton().isErsteScan()) {
                        Singleton.getSingleton().setErsteScan(true);
                    }
                } else setNullActualParam();
            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Log.d("img", "error");
                setNullActualParam();
            }
        });
    }

    public void request2(){
        iOpenWeather.loadWeatherCoord(lat.replace(",", "."), lon.replace(",", "."), "metric", "ru", BuildConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {

                if (response.body() != null && response.isSuccessful()) {
                    temperature = String.format("%.1f", response.body().getList().get(0).getMain().getTemp());
                    pressure = String.format("%d", response.body().getList().get(0).getMain().getPressure());
                    humidity = String.format("%d", response.body().getList().get(0).getMain().getHumidity());
                    windSpeed = String.format("%.1f", response.body().getList().get(0).getWind().getSpeed());
                    description = String.format("%s", response.body().getList().get(0).getWeather()[0].getDescription());
                    img = response.body().getList().get(0).getWeather()[0].getImg();
                    name = String.format("%s", response.body().getCity().getNameCity());
                    dt_txt = response.body().getList().get(0).getDt_txt();
                    DataParameters.getInstance().getDataListRequest().addAll(response.body().getList());

                    setActualParam();
                    fh.initRecycleWeather();
                    if(!Singleton.getSingleton().isErsteScan()) {

                        fh.requestAndUpdate();
                        Singleton.getSingleton().setErsteScan(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Log.d("img", "error");
                setNullActualParam();
            }
        });
    }

    private void setActualParam() {
        DataParameters.getInstance().setTemperature_actual(temperature);
        DataParameters.getInstance().setPress_actual(pressure);
        DataParameters.getInstance().setHumi_actual(humidity);
        DataParameters.getInstance().setWindSpeed_actual(windSpeed);
        DataParameters.getInstance().setDescript_actual(description);
        DataParameters.getInstance().setImg_actual(img);
        DataParameters.getInstance().setName(name);
    }

    private void setNullActualParam() {
        DataParameters.getInstance().setTemperature_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setPress_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setHumi_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setWindSpeed_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setDescript_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setImg_actual(App.getInstance().getResources().getString(R.string.noData));
        DataParameters.getInstance().setName(App.getInstance().getResources().getString(R.string.noData));
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
