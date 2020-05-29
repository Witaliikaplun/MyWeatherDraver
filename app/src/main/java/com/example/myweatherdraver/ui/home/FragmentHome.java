package com.example.myweatherdraver.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherdraver.BuildConfig;
import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.data.DataConversion;
import com.example.myweatherdraver.data.IOpenWeather;
import com.example.myweatherdraver.data.NetworkService;
import com.example.myweatherdraver.data.WeatherRequest;
import com.example.myweatherdraver.list_elements.CityFavourites;
import com.example.myweatherdraver.list_elements.WeatherAdapter;
import com.example.myweatherdraver.list_elements.WeatherSource;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    MainActivity act;
    IOpenWeather iOpenWeather;
    TextView textDescription;
    ImageView imageView;
    ImageView imageIcon;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        act = (MainActivity) getContext();
        imageView = root.findViewById(R.id.imageView2);
        imageIcon = root.findViewById(R.id.imageView3);

        iOpenWeather = NetworkService.getInstance().getiOpenWeather();

        setUnits();
        initRecycleWeather();
        viewTextPresSpeedHumi();
        reqRetrofitAndUpdateParam(Singleton.getSingleton().getCityForRequest(), BuildConfig.WEATHER_API_KEY);

        setImage("https://images.unsplash.com/photo-1564085398485-e17e00205e6b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60", imageView);

        return root;
    }

    private void setImage(String pach, ImageView iv) {
        try {
            Picasso.get().load(pach)
                    .fit()  //подогнать изображение под целевую imageView
                    .into(iv);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    private void reqRetrofitAndUpdateParam(String city, String keyApi) {
        iOpenWeather.loadWeather(city, "metric", "ru", keyApi).enqueue(new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                TextView textPress = act.findViewById(R.id.textUnitPress);
                TextView textTemp = act.findViewById(R.id.tv_Temperature);
                TextView textSpeed = act.findViewById(R.id.textUnitSpeed);
                TextView textCity = act.findViewById(R.id.textCity);
                TextView textHumi = act.findViewById(R.id.tv_textUnitHumi);
                textDescription = act.findViewById(R.id.textDescript);
                List list = Singleton.getSingleton().getListFav();

                if (response.body() != null && response.isSuccessful()) {
                    String temperature = String.format("%.1f", response.body().getMain().getTemp());
                    String pressure = String.format("%d", response.body().getMain().getPressure());
                    String humidity = String.format("%d", response.body().getMain().getHumidity());
                    String windSpeed = String.format("%d", response.body().getWind().getSpeed());
                    String description = String.format("%s", response.body().getWeather()[0].getDescription());
                    String img =   response.body().getWeather()[0].getImg();

                    setImage("http://openweathermap.org/img/wn/" + img + "@2x.png", imageIcon);

                    textHumi.setText(humidity);
                    textDescription.setText(description);
                    String[] arayCity = getResources().getStringArray(R.array.arrayCity);
                    textCity.setText(arayCity[Singleton.getSingleton().getPositionSpinner()]);

                    addCityFavourites(list, new CityFavourites(arayCity[Singleton.getSingleton().getPositionSpinner()],

                            temperature), arayCity[Singleton.getSingleton().getPositionSpinner()]);
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
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                Log.d("img", "error");
            }
        });
    }

    private void addCityFavourites(List list, CityFavourites e, String anObject) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().equals(anObject)) {
                flag = true;
                break;
            }
        }
        if (!flag)
            list.add(e);
    }

    private void setUnits() {
        TextView tvUnitsT = root.findViewById(R.id.tv_unitsTemperature);
        TextView tvUnitSpeed = root.findViewById(R.id.tv_units_speed);
        TextView tvUnitsPress = root.findViewById(R.id.tv_textUnitPress);

        tvUnitsT.setText((Singleton.getSingleton().getSwitchUnitsCF()) ? R.string.F : R.string.C);
        tvUnitSpeed.setText((Singleton.getSingleton().getSwitchUnitsSpeed()) ? R.string.unitsSpeed_km_h
                : R.string.unitsSpeed_m_sec);
        tvUnitsPress.setText((Singleton.getSingleton().getSwitchUnitsPres()) ? R.string.unitsPress_gPa
                : R.string.unitsPress_Hg);
    }

    private void initRecycleWeather() {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        WeatherSource ws = new WeatherSource(getResources());
        ArrayList listWeather = ws.build().getListWeather();
        WeatherAdapter weatherAdapter = new WeatherAdapter(listWeather);
        recyclerView.setAdapter(weatherAdapter);
        //декоратор-------------
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
        //----------------------
    }

    private void viewTextPresSpeedHumi() {
        LinearLayout layoutPress = root.findViewById(R.id.layautPress);
        LinearLayout layoutHumi = root.findViewById(R.id.layautHumi);
        LinearLayout layoutSpeed = root.findViewById(R.id.layautSpeed);

        if (Singleton.getSingleton().getSwitchPress()) layoutPress.setVisibility(View.VISIBLE);
        else layoutPress.setVisibility(View.GONE);

        if (Singleton.getSingleton().getSwitchHumil()) layoutHumi.setVisibility(View.VISIBLE);
        else layoutHumi.setVisibility(View.GONE);

        if (Singleton.getSingleton().getSwitchSpeed()) layoutSpeed.setVisibility(View.VISIBLE);
        else layoutSpeed.setVisibility(View.GONE);
    }


}
