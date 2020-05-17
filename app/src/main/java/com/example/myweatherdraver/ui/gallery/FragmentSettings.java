package com.example.myweatherdraver.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.google.android.material.snackbar.Snackbar;

public class FragmentSettings extends Fragment {


    private Switch sPress;
    private Switch sSpeed;
    private Switch sHumi;
    private Switch sTheme;
    Spinner spinner;
    MainActivity act;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_setting, container, false);
        act = (MainActivity) getContext();




        sPress = root.findViewById(R.id.switchPress);
        sSpeed = root.findViewById(R.id.switchSpeed);
        sHumi = root.findViewById(R.id.switchHumi);
        sTheme = root.findViewById(R.id.switchTheme);
        TextView textCity = root.findViewById(R.id.textView6);

        String[] arrayCity = getResources().getStringArray(R.array.arrayCity);

        spinner = (Spinner) root.findViewById(R.id.spiner);
        spinerMethod(textCity, arrayCity, spinner);

        if(Singleton.getSingleton().getSwitchPress()) sPress.setChecked(true);
        else sPress.setChecked(false);

        if(Singleton.getSingleton().getSwitchHumil()) sHumi.setChecked(true);
        else sHumi.setChecked(false);

        if(Singleton.getSingleton().getSwitchSpeed()) sSpeed.setChecked(true);
        else sSpeed.setChecked(false);





        sPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sPress.isChecked()) {
                    sPress.setChecked(false);
                    Snackbar.make(root, R.string.textchengPress,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchPress(true);
                            sPress.setChecked(true);
                        }
                    }).show();
                }
                else {
                    sPress.setChecked(true);

                    Snackbar.make(root, R.string.textchengPress_2,
                            Snackbar.LENGTH_LONG).setAction(R.string.rem_pres, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchPress(false);
                            sPress.setChecked(false);
                        }
                    }).show();
                }
            }
        });

        sHumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sHumi.isChecked()) {
                    sHumi.setChecked(false);
                    Snackbar.make(root, R.string.textshowHumi,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchHumil(true);
                            sHumi.setChecked(true);
                        }
                    }).show();
                }
                else {
                    sHumi.setChecked(true);
                    Snackbar.make(root, R.string.textremiveHumi,
                            Snackbar.LENGTH_LONG).setAction(R.string.rem_pres, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchHumil(false);
                            sHumi.setChecked(false);
                        }
                    }).show();
                }
            }
        });

        sSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sSpeed.isChecked()){
                    sSpeed.setChecked(false);
                    Snackbar.make(root, R.string.show_speed,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchSpeed(true);
                            sSpeed.setChecked(true);
                        }
                    }).show();
                }
                else{
                    sSpeed.setChecked(true);
                    Snackbar.make(root, R.string.rem_speed,
                            Snackbar.LENGTH_LONG).setAction(R.string.remove_but, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchSpeed(false);
                            sSpeed.setChecked(false);
                        }
                    }).show();
                }
            }
        });


        return root;
    }

    private void spinerMethod(final TextView textCity, final String[] arrayCity, Spinner spinner) {
        //адаптер----------------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        // заголовок--------------------------------------------------
        spinner.setPrompt("Title");
        // выделяем элемент ------------------------------
        spinner.setSelection(Singleton.getSingleton().getPosition());

        // устанавливаем обработчик нажатия---------------------------
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position){
                    case 0: textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPosition(0);
                        act.getReq().init();
                        break;

                    case 1: textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPosition(1);
                        act.getReq().init();
                        break;

                    case 2: textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPosition(2);
                        act.getReq().init();
                        break;
                }
                Singleton.getSingleton().setCity(textCity.getText().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
