package com.example.myweatherdraver.ui.favourites;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherdraver.R;
import com.example.myweatherdraver.Singleton;
import com.example.myweatherdraver.db.App;
import com.example.myweatherdraver.list_elements.CityAdapter;

public class FragmentFavourites extends Fragment {
    RecyclerView recyclerView;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourites, container, false);
        initRecycleFav(root);
        view = root;
        return root;
    }

    private void initRecycleFav(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewFavourites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        CityAdapter cityAdapter = new CityAdapter(Singleton.getSingleton().getCityFavSourceForDB().getListCity(), this);
        recyclerView.setAdapter(cityAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        int id = item.getItemId();
        switch (id) {
            case R.id.remove_context:
                //adapter.removeItem(adapter.getMenuPosition());
                Singleton.getSingleton().getCityFavSourceForDB().delCity(App.getInstance().getICityFavDAO().getAllCityFav().get(Singleton.getSingleton().getMenuPosition()));
                initRecycleFav(view);
                Toast.makeText(getContext(), "нажали удалить меню", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.request_context:
                //adapter.clearItems();
                Singleton.getSingleton().setCityForRequest(Singleton.getSingleton().getCityFavSourceForDB().getListCity().get(Singleton.getSingleton().getMenuPosition()).getCity());
                Singleton.getSingleton().getRequestRetrofit().request();
                Toast.makeText(getContext(), "нажали обновить данные", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
