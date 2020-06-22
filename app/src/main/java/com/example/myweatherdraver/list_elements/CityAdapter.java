package com.example.myweatherdraver.list_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myweatherdraver.R;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityFavViewHolder> {
    List<CityFavourites> listFav;

    private Fragment fragment;
    private int menuPosition;

    public CityAdapter(List<CityFavourites> listFav, Fragment fragment) {
        this.listFav = listFav;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CityFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourit, parent, false);
        return new CityFavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityFavViewHolder holder, final int position) {
        TextView textElement = holder.getTextElement();

        holder.cityFav.setText(listFav.get(position).getCity());
        holder.citiFavTemp.setText(listFav.get(position).getTemperature());
        holder.citiFavTime.setText(listFav.get(position).getDateAndTime());

        // Определяем текущую позицию в списке
        textElement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                menuPosition = position;
                return false;
            }
        });

        // Так регистрируется контекстное меню
        if (fragment != null){
            fragment.registerForContextMenu(textElement);
        }
    }

    @Override
    public int getItemCount() {
        return listFav.size();
    }

    public class CityFavViewHolder extends RecyclerView.ViewHolder {
        private TextView textElement;
        TextView cityFav;
        TextView citiFavTemp;
        TextView citiFavTime;

        public CityFavViewHolder(@NonNull View itemView) {
            super(itemView);

            textElement = itemView.findViewById(R.id.cityFav);

            cityFav = (TextView) itemView.findViewById(R.id.cityFav);
            citiFavTemp = (TextView) itemView.findViewById(R.id.cityFavtemp);
            citiFavTime = (TextView) itemView.findViewById(R.id.timeFav);
        }

        public TextView getTextElement() {
            return textElement;
        }
    }
}
