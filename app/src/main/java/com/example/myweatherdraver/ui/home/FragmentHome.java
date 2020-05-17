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
import com.example.myweatherdraver.list_elements.WeatherAdapter;
import com.example.myweatherdraver.list_elements.WeatherSource;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    MainActivity act;
    private TextView textCity;
    private TextView textTemp;
    private TextView textUnitPres;
    private TextView textUnitSpeed;
    private TextView textUnitHumi;
    private TextView textDescription;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        act = (MainActivity) getContext();
        textTemp = root.findViewById(R.id.textView3);
        textCity = root.findViewById(R.id.textCity);
        textUnitPres = root.findViewById(R.id.textUnitPress);
        textUnitSpeed = root.findViewById(R.id.textUnitSpeed);
        textUnitHumi = root.findViewById(R.id.textUnitHumi);
        textDescription = root.findViewById(R.id.textDescript);

        //act.getReq().init();

        textTemp.setText(act.getReq().getTemperature());
        textUnitSpeed.setText(act.getReq().getWindSpeed());
        textUnitPres.setText(act.getReq().getPressure());
        textUnitHumi.setText(act.getReq().getHumidity());
        textDescription.setText(act.getReq().getDescription());
        textCity.setText(Singleton.getSingleton().getCity());

        initRecycleWeather();
        viewTextPresSpeedHumi();

        return root;
    }

    private void initRecycleWeather() {
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
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

        if(Singleton.getSingleton().getSwitchPress()) layoutPress.setVisibility(View.VISIBLE);
        else layoutPress.setVisibility(View.GONE);

        if(Singleton.getSingleton().getSwitchHumil()) layoutHumi.setVisibility(View.VISIBLE);
        else layoutHumi.setVisibility(View.GONE);

        if(Singleton.getSingleton().getSwitchSpeed()) layoutSpeed.setVisibility(View.VISIBLE);
        else layoutSpeed.setVisibility(View.GONE);


    }
}
