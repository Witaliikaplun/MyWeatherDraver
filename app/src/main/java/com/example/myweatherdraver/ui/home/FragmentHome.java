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
import com.example.myweatherdraver.list_elements.WeatherSource;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private MainActivity act;
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
        act = (MainActivity) getContext();
        imageView = root.findViewById(R.id.imageView2);
        imageIcon = root.findViewById(R.id.imageTWeath);
        list = Singleton.getSingleton().getListFav();
        iOpenWeather = NetworkService.getInstance().getiOpenWeather();
        requestRetrofit = new RequestRetrofit(iOpenWeather, this);

        getRequestRetrofit().setCity(Singleton.getSingleton().getCityForRequest());
        Singleton.getSingleton().setRequestRetrofit(requestRetrofit);



        if (!Singleton.getSingleton().isErsteScan()) getRequestRetrofit().request();
        else requestAndUpdate();


        ICityFavDAO iCityFavDAO = App.getInstance().getICityFavDAO();
        cityFavSourceForDB = new CityFavSourceForDB(iCityFavDAO, list);
        Singleton.getSingleton().setCityFavSourceForDB(cityFavSourceForDB);

        setUnits();
        initRecycleWeather();
        viewTextPresSpeedHumi();

        setImage("https://images.unsplash.com/photo-1564085398485-e17e00205e6b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60", imageView);



        return root;
    }

    private void setImage(String pach, ImageView iv) {
        try {
            Picasso.get().load(pach)
                    .fit()  //подогнать изображение под целевую imageView
                    .into(iv);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void requestAndUpdate() {
        TextView textPressActual;
        TextView textTempActual;
        TextView textSpeedActual;
        TextView textCityActual;
        TextView textHumiActual;
        TextView textDescriptActual;
        try {
            textPressActual = act.findViewById(R.id.textUnitPress);
            textTempActual = act.findViewById(R.id.tv_Temperature);
            textSpeedActual = act.findViewById(R.id.textUnitSpeed);
            textCityActual = act.findViewById(R.id.textCity);
            textHumiActual = act.findViewById(R.id.tv_textUnitHumi);
            textDescriptActual = act.findViewById(R.id.textDescript);

            setImage("https://openweathermap.org/img/wn/" + getRequestRetrofit().getImg() + "@2x.png", imageIcon);

            Log.d("param2", "внутри" );
            Log.d("param2", "temp_actual---" + DataParameters.getInstance().getTemperature_actual());
            Log.d("param2", "press_actual---" + DataParameters.getInstance().getPress_actual());
            Log.d("param2", "humi_actual---" + DataParameters.getInstance().getHumi_actual());
            Log.d("param2", "wind_actual---" + DataParameters.getInstance().getWindSpeed_actual());
            Log.d("param2", "desc_actual---" + DataParameters.getInstance().getDescript_actual());
            Log.d("param2", "name---" + DataParameters.getInstance().getName());




            textHumiActual.setText(DataParameters.getInstance().getHumi_actual());
            textDescriptActual.setText(DataParameters.getInstance().getDescript_actual());

            textCityActual.setText(DataParameters.getInstance().getName());

            addCityFavourites(new CityFavourites(DataParameters.getInstance().getName(),
                    getRequestRetrofit().getTemperature()));
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

        }
    }




    private void addCityFavourites(CityFavourites e) {
        cityFavSourceForDB.addCity(e);
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

    public RequestRetrofit getRequestRetrofit() {
        return requestRetrofit;
    }




}
