package com.example.myweatherdraver.data;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.Singleton;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

public class Request {

    private static final String TAG = "WEATHER_MY";
    private String city;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private  String description;
    private String id_city;
        public void init() {
            try {

                final URL uri = new URL(request(Singleton.getSingleton().getPosition()) + BuildConfig.WEATHER_API_KEY);
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET");
                            urlConnection.setReadTimeout(10000);
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                            final String result = getLines(in);
                            Gson gson = new Gson();

                            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);

                            // Возвращаемся к основному потоку
                            handler.post( new Runnable() {
                                @Override
                                public void run() {
                                    displayWeather(weatherRequest);
                                    Log.d("rez", result);
                                }
                            });


                        } catch (Exception e) {
                            Log.e(TAG, "Fail Connection", e);
                            e.printStackTrace();
                        }
                    }


                    @RequiresApi(api = Build.VERSION_CODES.N)
                    private String getLines(BufferedReader in) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }
                }).start();
            } catch (Exception e) {
                Log.e(TAG, "Fail URL", e);
                e.printStackTrace();
            }
        }

    private void displayWeather(WeatherRequest weatherRequest) {
        city = (weatherRequest.getName());
        temperature = String.format("%.1f", weatherRequest.getMain().getTemp());
        pressure = String.format("%d", weatherRequest.getMain().getPressure());
        humidity = String.format("%d", weatherRequest.getMain().getHumidity());
        windSpeed = String.format("%d", weatherRequest.getWind().getSpeed());
        description = String.format("%s", weatherRequest.getWeather()[0].getDescription());
        id_city = String.format("%d", weatherRequest.getId());
        //Log.d("rez", id_city);

    }

    public String request(int num) {
            String reqest = "";
            switch (num) {
                case 0:
                    reqest = "https://api.openweathermap.org/data/2.5/weather?lat=45.04&lon=38.97&units=metric&appid=";
                    break;
                case 1:
                    reqest = "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid=";
                    break;
                case 2:
                    reqest = "https://api.openweathermap.org/data/2.5/weather?lat=59.93&lon=30.31&units=metric&appid=";
                    break;
            }
            return reqest;
        }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getId_city() {
        return id_city;
    }
}
