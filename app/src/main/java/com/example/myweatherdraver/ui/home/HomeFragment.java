package com.example.myweatherdraver.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.list_elements.WeatherAdapter;
import com.example.myweatherdraver.list_elements.WeatherSource;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    MainActivity act;
    private TextView textCity;
    private TextView textTemp;
    private TextView textUnitPres;
    private TextView textUnitSpeed;
    private TextView textUnitHumi;
    private TextView textDescription;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        act = (MainActivity) getContext();

        textTemp = root.findViewById(R.id.textView3);
        textCity = root.findViewById(R.id.textView2);

        textUnitPres = root.findViewById(R.id.textUnitPress);
        textUnitSpeed = root.findViewById(R.id.textUnitSpeed);
        textUnitHumi = root.findViewById(R.id.textUnitHumi);
        textDescription = root.findViewById(R.id.textDescript);

        act.getReq().init();

        textTemp.setText(act.getReq().getTemperature());

        textUnitSpeed.setText(act.getReq().getWindSpeed());
        textUnitPres.setText(act.getReq().getPressure());
        textUnitHumi.setText(act.getReq().getHumidity());
        textDescription.setText(act.getReq().getDescription());




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






        return root;
    }
}
