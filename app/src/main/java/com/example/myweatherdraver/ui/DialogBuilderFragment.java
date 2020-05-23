package com.example.myweatherdraver.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;

public class DialogBuilderFragment extends DialogFragment {
    private ToggleButton tb;

    public DialogBuilderFragment(ToggleButton tb){
        this.tb = tb;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_dialog_2);
        builder.setMessage(R.string.message2);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!Singleton.getSingleton().getSwitchUnitsSpeed()){
                    tb.setForeground(getResources().getDrawable(R.drawable.img4));
                    Singleton.getSingleton().setSwitchUnitsSpeed(true);
                }else {
                    tb.setForeground(getResources().getDrawable(R.drawable.img3));
                    Singleton.getSingleton().setSwitchUnitsSpeed(false);
                }
            }
        });
        builder.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }
}
