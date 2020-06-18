package com.example.myweatherdraver.list_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myweatherdraver.R;
import com.example.myweatherdraver.data.DataParameters;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    ArrayList<WeatherForRecicle> weathers;

    public WeatherAdapter(ArrayList<WeatherForRecicle> weathers) {
        this.weathers = weathers;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{

        TextView desc;
        TextView tvTime;
        ImageView img;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image);
            desc = (TextView) itemView.findViewById(R.id.descript);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int position) {
        weatherViewHolder.desc.setText(weathers.get(position).getDescription());
        weatherViewHolder.img.setImageResource(weathers.get(position).getImg());
        weatherViewHolder.tvTime.setText(weathers.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }


}
