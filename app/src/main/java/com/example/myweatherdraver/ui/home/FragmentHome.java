package com.example.myweatherdraver.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myweatherdraver.list_elements.WeatherAdapter;
import com.example.myweatherdraver.list_elements.WeatherSource;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    MainActivity act;
    private TextView textCity;
    private TextView textTemp;
    private TextView textPres;
    private TextView textSpeed;
    private TextView textHumi;
    private TextView textDescription;
    private TextView tvUnitsT;
    private TextView tvUnitSpeed;
    private TextView tvUnitsPress;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        act = (MainActivity) getContext();
        textTemp = root.findViewById(R.id.tv_Temperature);
        textCity = root.findViewById(R.id.textCity);
        textPres = root.findViewById(R.id.textUnitPress);
        textSpeed = root.findViewById(R.id.textUnitSpeed);
        textHumi = root.findViewById(R.id.textUnitHumi);
        textDescription = root.findViewById(R.id.textDescript);
        tvUnitsT = root.findViewById(R.id.tv_unitsTemperature);
        tvUnitSpeed = root.findViewById(R.id.tv_units_speed);
        tvUnitsPress = root.findViewById(R.id.tv_textUnitPress);

        update();

        textHumi.setText(act.getReq().getHumidity());
        textDescription.setText(act.getReq().getDescription());
        textCity.setText(Singleton.getSingleton().getCity());

        setUnits();
        initRecycleWeather();
        viewTextPresSpeedHumi();
        return root;
    }

    private void update() {
        try {
            textPres.setText(new DataConversion(Double.parseDouble(act.getReq().getPressure()),
                    Singleton.getSingleton().getSwitchUnitsPres(), 0).conversionThread());

            textTemp.setText(new DataConversion(Double.parseDouble(act.getReq().getTemperature().replace(',', '.')),
                    Singleton.getSingleton().getSwitchUnitsCF(), 1).conversionThread());

            textSpeed.setText(String.valueOf(new DataConversion(Double.parseDouble(act.getReq().getWindSpeed()),
                    Singleton.getSingleton().getSwitchUnitsSpeed(), 2).conversionThread()));
        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void setUnits() {
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
