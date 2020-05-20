package com.example.myweatherdraver.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;

public class DialogCustomFragment extends DialogFragment {
    private ToggleButton tb;

    public DialogCustomFragment(ToggleButton tb) {
        this.tb = tb;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.alert_dialog_my, null);
        Button b_yes = view.findViewById(R.id.b_ad_yes);
        Button b_no = view.findViewById(R.id.b_ad_no);

        b_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Singleton.getSingleton().getSwitchUnitsPres()){
                    tb.setForeground(getResources().getDrawable(R.drawable.img6));
                    Singleton.getSingleton().setSwitchUnitsPres(true);
                }else {
                    tb.setForeground(getResources().getDrawable(R.drawable.img5));
                    Singleton.getSingleton().setSwitchUnitsPres(false);
                }
                Toast.makeText(getContext(), "нажата кнопка да", Toast.LENGTH_SHORT).show();
                dismiss();          //закрываем диалог
            }
        });

        b_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "нажата кнопка нет", Toast.LENGTH_SHORT).show();
                dismiss();          //закрываем диалог
            }
        });


        return view;
    }
}
