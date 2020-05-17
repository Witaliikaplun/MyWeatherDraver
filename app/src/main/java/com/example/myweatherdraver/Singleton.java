package com.example.myweatherdraver;

import com.example.myweatherdraver.list_elements.CityFavourites;

import java.util.ArrayList;

public final class Singleton {
    private static Singleton instance = null;
    private boolean switchPress;
    private boolean switchHumil;
    private boolean switchSpeed;
    private int position;
    private String city;
    ArrayList<CityFavourites> listFav;
    private static final Object obj = new Object();

    private Singleton(){
        switchPress = false;
        listFav = new ArrayList<>();

    }

    public void setListFav(ArrayList<CityFavourites> listFav) {
        this.listFav = listFav;
    }

    public ArrayList<CityFavourites> getListFav() {
        return listFav;
    }

    public boolean getSwitchPress() {
        return switchPress;
    }

    public void setSwitchPress(boolean switchPress) {
        this.switchPress = switchPress;
    }

    public boolean getSwitchHumil() {
        return switchHumil;
    }

    public void setSwitchHumil(boolean switchHumil) {
        this.switchHumil = switchHumil;
    }

    public boolean getSwitchSpeed() {
        return switchSpeed;
    }

    public void setSwitchSpeed(boolean switchPress) {
        this.switchSpeed = switchPress;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public static Singleton getSingleton(){
            if(instance == null){
                instance = new Singleton();
        }
        return instance;
    }
}
