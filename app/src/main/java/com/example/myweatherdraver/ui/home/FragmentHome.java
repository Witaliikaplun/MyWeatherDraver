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

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.data.DataConversion;
import com.example.myweatherdraver.data.DataParameters;
import com.example.myweatherdraver.data.IOpenWeather;
import com.example.myweatherdraver.data.NetworkService;
import com.example.myweatherdraver.data.RequestRetrofit;
import com.example.myweatherdraver.db.App;
import com.example.myweatherdraver.db.CityFavSourceForDB;
import com.example.myweatherdraver.db.ICityFavDAO;
import com.example.myweatherdraver.list_elements.CityFavourites;
import com.example.myweatherdraver.list_elements.WeatherAdapter;
import com.example.myweatherdraver.list_elements.WeatherAdapterDay;
import com.example.myweatherdraver.list_elements.WeatherSource;
import com.example.myweatherdraver.list_elements.WeatherSourceDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewDay;
    private IOpenWeather iOpenWeather;
    private RequestRetrofit requestRetrofit;

    private ImageView imageView;
    private ImageView imageIcon;
    private CityFavSourceForDB cityFavSourceForDB;
    private List list;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = root.findViewById(R.id.imageView2);
        imageIcon = root.findViewById(R.id.imageTWeath);
        list = Singleton.getSingleton().getListFav();
        iOpenWeather = NetworkService.getInstance().getiOpenWeather();
        requestRetrofit = new RequestRetrofit(iOpenWeather, this);

        requestRetrofit.setCity(Singleton.getSingleton().getCityForRequest());
        Singleton.getSingleton().setRequestRetrofit(requestRetrofit);

        if (!Singleton.getSingleton().isErsteScan()){
            getRequestRetrofit().request();
            ICityFavDAO iCityFavDAO = App.getInstance().getICityFavDAO();
            cityFavSourceForDB = new CityFavSourceForDB(iCityFavDAO, list);
            Singleton.getSingleton().setCityFavSourceForDB(cityFavSourceForDB);
        }
        else {
            requestAndUpdate();
            initRecycleWeather();
            initRecycleWeatherDay();
            setImage(DataParameters.getInstance().getUrlImage(), imageView);
        }
        setUnits();

        viewTextPresSpeedHumi();

        return root;
    }

    public void setImage(String pach, ImageView iv) {
        try {
            Picasso.get().load(pach)
                    .fit()  //подогнать изображение под целевую imageView
                    .into(iv);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void requestAndUpdate() {
        TextView textPressActual;
        TextView textTempActual;
        TextView textSpeedActual;
        TextView textCityActual;
        TextView textHumiActual;
        TextView textDescriptActual;

        textPressActual = root.findViewById(R.id.textUnitPress);
        textTempActual = root.findViewById(R.id.tv_Temperature);
        textSpeedActual = root.findViewById(R.id.textUnitSpeed);
        textCityActual = root.findViewById(R.id.textCity);
        textHumiActual = root.findViewById(R.id.tv_textUnitHumi);
        textDescriptActual = root.findViewById(R.id.textDescript);

        try {

            setImage("https://openweathermap.org/img/wn/" + DataParameters.getInstance().getImg_actual() + "@2x.png", imageIcon);

            textHumiActual.setText(DataParameters.getInstance().getHumi_actual());
            textDescriptActual.setText(DataParameters.getInstance().getDescript_actual());
            textCityActual.setText(DataParameters.getInstance().getName());
            try {
                textPressActual.setText(new DataConversion(Double.parseDouble(DataParameters.getInstance().getPress_actual()),
                        Singleton.getSingleton().getSwitchUnitsPres(), 0).conversion());

                textTempActual.setText(new DataConversion(Double.parseDouble(DataParameters.getInstance().getTemperature_actual().replace(',', '.')),
                        Singleton.getSingleton().getSwitchUnitsCF(), 1).conversion());

                textSpeedActual.setText(String.valueOf(new DataConversion(Double.parseDouble(DataParameters.getInstance().getWindSpeed_actual()),
                        Singleton.getSingleton().getSwitchUnitsSpeed(), 2).conversion()));

            } catch (NumberFormatException | NullPointerException ex) {
                ex.printStackTrace();
            }
        } catch (RuntimeException ex) {
            Log.d("param2", "эксепшн");
        }
                    addCityFavourites(new CityFavourites(DataParameters.getInstance().getName(),
                            DataParameters.getInstance().getTemperature_actual()));
    }

    private void addCityFavourites(CityFavourites e) {
        Singleton.getSingleton().getCityFavSourceForDB().addCity(e);
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

    public void initRecycleWeather() {
        try {
            recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
            WeatherSource ws = new WeatherSource(getResources());
            ArrayList listWeather = ws.build().getListWeather();
            WeatherAdapter weatherAdapter = new WeatherAdapter(listWeather);
            recyclerView.setAdapter(weatherAdapter);
        } catch (IllegalStateException e){
        }
    }

    public void initRecycleWeatherDay() {
        try {
            recyclerViewDay = (RecyclerView) root.findViewById(R.id.recyclerViewDay);
            recyclerViewDay.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            WeatherSourceDay wsDay = new WeatherSourceDay(getResources());
            ArrayList listWeather = wsDay.build().getListWeatherDay();
            WeatherAdapterDay weatherAdapterDay = new WeatherAdapterDay(listWeather);
            recyclerViewDay.setAdapter(weatherAdapterDay);
        } catch (IllegalStateException e){
    }
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

    public RequestRetrofit getRequestRetrofit() {
        return requestRetrofit;
    }
}
