package com.example.myweatherdraver.list_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myweatherdraver.R;
import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityFavViewHolder> {
    ArrayList<CityFavourites> listFav;

    public CityAdapter(ArrayList<CityFavourites> listFav) {
        this.listFav = listFav;
    }

    @NonNull
    @Override
    public CityFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourit, parent, false);
        return new CityFavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityFavViewHolder holder, int position) {
        holder.cityFav.setText(listFav.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return listFav.size();
    }

    public class CityFavViewHolder extends RecyclerView.ViewHolder {
        TextView cityFav;
        public CityFavViewHolder(@NonNull View itemView) {
            super(itemView);
            cityFav = (TextView) itemView.findViewById(R.id.cityFav);
        }
    }
}
