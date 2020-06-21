package com.example.myweatherdraver.list_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherdraver.R;

import java.util.ArrayList;

public class WeatherAdapterDay extends RecyclerView.Adapter<WeatherAdapterDay.WeatherViewHolderDay> {
    ArrayList<WeatherForRecicleDay> weathersDay;

    public WeatherAdapterDay(ArrayList<WeatherForRecicleDay> weathers) {
        this.weathersDay = weathers;
    }

    public class WeatherViewHolderDay extends RecyclerView.ViewHolder{

        TextView day;
        TextView dayWeek;
        TextView temperature;
        ImageView img;
        public WeatherViewHolderDay(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_day_image);
            day = (TextView) itemView.findViewById(R.id.tv_day);
            dayWeek = (TextView) itemView.findViewById(R.id.tv_day_of_week);
            temperature = (TextView) itemView.findViewById(R.id.tv_day_temp);
        }
    }

    @NonNull
    @Override
    public WeatherViewHolderDay onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_day, viewGroup, false);
        return new WeatherViewHolderDay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolderDay weatherViewHolder, int position) {
        weatherViewHolder.day.setText(weathersDay.get(position).getDay());
        weatherViewHolder.dayWeek.setText(weathersDay.get(position).getDayWeek());
        weatherViewHolder.img.setImageResource(weathersDay.get(position).getImgDay());
        weatherViewHolder.temperature.setText(weathersDay.get(position).getTemperatureDay());

    }

    @Override
    public int getItemCount() {
        return weathersDay.size();
    }


}
