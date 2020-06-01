package com.example.myweatherdraver.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myweatherdraver.MainActivity;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.data.IOpenWeather;
import com.example.myweatherdraver.data.RequestRetrofit;
import com.example.myweatherdraver.ui.DialogBuilderFragment;
import com.example.myweatherdraver.ui.DialogCustomFragment;
import com.example.myweatherdraver.ui.home.FragmentHome;
import com.google.android.material.snackbar.Snackbar;

public class FragmentSettings extends Fragment {
    private Switch sPress;
    private Switch sSpeed;
    private Switch sHumi;
    private Switch sTheme;
    Spinner spinner;
    private ToggleButton tbCF;
    private ToggleButton tb_m_km;
    private ToggleButton tb_mm_gPa;
    private DialogBuilderFragment dialogBuilderFragment;
    private DialogCustomFragment dialogCustomFragment;
    private IOpenWeather iOpenWeather;
    RequestRetrofit requestRetrofit;
    MainActivity act;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_setting, container, false);
        act = (MainActivity) getContext();

        sPress = root.findViewById(R.id.switchPress);
        sSpeed = root.findViewById(R.id.switchSpeed);
        sHumi = root.findViewById(R.id.switchHumi);
        sTheme = root.findViewById(R.id.switchTheme);
        tbCF = root.findViewById(R.id.tbCelsFareng);
        tb_m_km = root.findViewById(R.id.tb_MS_KM);
        tb_mm_gPa = root.findViewById(R.id.tb_Pres);

        dialogBuilderFragment = new DialogBuilderFragment(tb_m_km);
        dialogCustomFragment = new DialogCustomFragment(tb_mm_gPa);
        requestRetrofit = Singleton.getSingleton().getRequestRetrofit();
        TextView textCity = root.findViewById(R.id.textView6);

        String[] arrayCity = getResources().getStringArray(R.array.arrayCity);

        spinner = (Spinner) root.findViewById(R.id.spiner);
        spinerMethod(textCity, arrayCity, spinner);

        if (Singleton.getSingleton().getSwitchPress()) sPress.setChecked(true);
        else sPress.setChecked(false);

        if (Singleton.getSingleton().getSwitchHumil()) sHumi.setChecked(true);
        else sHumi.setChecked(false);

        if (Singleton.getSingleton().getSwitchSpeed()) sSpeed.setChecked(true);
        else sSpeed.setChecked(false);

        if (Singleton.getSingleton().getSwitchTheme()) sTheme.setChecked(true);
        else sTheme.setChecked(false);

        initChekTB(tbCF, getResources().getDrawable(R.drawable.img1),
                getResources().getDrawable(R.drawable.img2), Singleton.getSingleton().getSwitchUnitsCF());

        initChekTB(tb_m_km, getResources().getDrawable(R.drawable.img3),
                getResources().getDrawable(R.drawable.img4), Singleton.getSingleton().getSwitchUnitsSpeed());

        initChekTB(tb_mm_gPa, getResources().getDrawable(R.drawable.img5),
                getResources().getDrawable(R.drawable.img6), Singleton.getSingleton().getSwitchUnitsPres());

        sPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPress.isChecked()) {
                    sPress.setChecked(false);
                    Snackbar.make(root, R.string.textchengPress,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchPress(true);
                            sPress.setChecked(true);
                        }
                    }).show();
                } else {
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
                if (sHumi.isChecked()) {
                    sHumi.setChecked(false);
                    Snackbar.make(root, R.string.textshowHumi,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchHumil(true);
                            sHumi.setChecked(true);
                        }
                    }).show();
                } else {
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
                if (sSpeed.isChecked()) {
                    sSpeed.setChecked(false);
                    Snackbar.make(root, R.string.show_speed,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton.getSingleton().setSwitchSpeed(true);
                            sSpeed.setChecked(true);
                        }
                    }).show();
                } else {
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

        sTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sTheme.isChecked()) Singleton.getSingleton().setSwitchTheme(true);
                else Singleton.getSingleton().setSwitchTheme(false);
                getActivity().recreate();// пересоздать активити
            }
        });

        tbCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbCF.setChecked(false);
                initAlertdialog1();
            }
        });

        tb_m_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb_m_km.setChecked(false);
                dialogBuilderFragment.show(((MainActivity) getContext()).getSupportFragmentManager(),
                        "dialogBuilder");
            }
        });

        tb_mm_gPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb_mm_gPa.setChecked(false);
                dialogCustomFragment.show(((MainActivity) getContext()).getSupportFragmentManager(),
                        "dialogCustom");
            }
        });
        return root;
    }

    private void initChekTB(ToggleButton tb, Drawable img1, Drawable img2, boolean check) {
        if (check) {
            tb.setChecked(true);
            tb.setForeground(img2);
        } else {
            tb.setChecked(false);
            tb.setForeground(img1);
        }
    }

    private void initAlertdialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(R.string.alert_dialog_1);
        builder.setMessage(R.string.message);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act, "нажали кнопку да", Toast.LENGTH_SHORT).show();
                if (!Singleton.getSingleton().getSwitchUnitsCF()) {
                    tbCF.setForeground(getResources().getDrawable(R.drawable.img2));
                    Singleton.getSingleton().setSwitchUnitsCF(true);
                } else {
                    tbCF.setForeground(getResources().getDrawable(R.drawable.img1));
                    Singleton.getSingleton().setSwitchUnitsCF(false);
                }
            }
        });
        builder.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act, "нажали кнопку нет", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void spinerMethod(final TextView textCity, final String[] arrayCity, Spinner spinner) {
        //адаптер----------------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        // заголовок--------------------------------------------------
        spinner.setPrompt("Title");
        // выделяем элемент ------------------------------
        spinner.setSelection(Singleton.getSingleton().getPositionSpinner());

        // устанавливаем обработчик нажатия---------------------------
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position) {
                    case 0:
                        textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPositionSpinner(0);
                        Singleton.getSingleton().setCityForRequest("Krasnodar");
                        requestRetrofit.request();
                        break;
                    case 1:
                        textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPositionSpinner(1);
                        Singleton.getSingleton().setCityForRequest("Moscow");
                        requestRetrofit.request();
                        break;
                    case 2:
                        textCity.setText(arrayCity[position]);
                        Singleton.getSingleton().setPositionSpinner(2);
                        Singleton.getSingleton().setCityForRequest("Saint Petersburg");
                        requestRetrofit.request();
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}

