package com.example.myweatherdraver.data;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
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
    private Thread t1;
    MainActivity act;

    public Request(MainActivity act){
        this.act = act;
    }
        public void init() {
            try {
                final URL uri = new URL(request(Singleton.getSingleton().getPositionSpinner()) + BuildConfig.WEATHER_API_KEY);
                final Handler handler = new Handler();
                t1 = new Thread(new Runnable() {
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
                            displayWeather(weatherRequest);
                            updateParam();
                        } catch (Exception e) {
                            Log.e(TAG, "Fail Connection", e);
                            e.printStackTrace();
                        }
                    }
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    private String getLines(BufferedReader in) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }
                });
                t1.start();

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
    }

    private void updateParam(){
        TextView textPress = act.findViewById(R.id.textUnitPress);
        TextView textTemp = act.findViewById(R.id.tv_Temperature);
        TextView textSpeed = act.findViewById(R.id.textUnitSpeed);
        try {
             textPress.setText(new DataConversion(Double.parseDouble(pressure),
                    Singleton.getSingleton().getSwitchUnitsPres(), 0).conversion());

            textTemp.setText(new DataConversion(Double.parseDouble(temperature.replace(',', '.')),
                    Singleton.getSingleton().getSwitchUnitsCF(), 1).conversion());

            textSpeed.setText(String.valueOf(new DataConversion(Double.parseDouble(windSpeed),
                    Singleton.getSingleton().getSwitchUnitsSpeed(), 2).conversion()));
        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
        }
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

    public String getDescription() {
        return description;
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

    public String getId_city() {
        return id_city;
    }

}
