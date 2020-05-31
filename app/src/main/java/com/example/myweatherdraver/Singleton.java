package com.example.myweatherdraver;

import com.example.myweatherdraver.list_elements.CityFavourites;

import java.util.ArrayList;

public final class Singleton {
    private static Singleton instance = null;
    private boolean switchPress;
    private boolean switchHumil;
    private boolean switchSpeed;
    private int positionSpinner;
    private String cityForRequest;
    ArrayList<CityFavourites> listFav;
    private boolean switchTheme;
    private boolean switchUnitsCF;
    private boolean switchUnitsSpeed;
    private boolean switchUnitsPres;
    private String imageResponse;

    private Singleton(){
        switchPress = false;
        cityForRequest = "Krasnodar";
        listFav = new ArrayList<>();
    }

    public boolean getSwitchTheme() {
        return switchTheme;
    }

    public void setSwitchTheme(boolean switchTeme) {
        this.switchTheme = switchTeme;
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

    public void setPositionSpinner(int positionSpinner) {
        this.positionSpinner = positionSpinner;
    }

    public int getPositionSpinner() {
        return positionSpinner;
    }

    public void setSwitchUnitsCF(boolean switchUnitsCF) {
        this.switchUnitsCF = switchUnitsCF;
    }

    public boolean getSwitchUnitsCF() {
        return switchUnitsCF;
    }

    public boolean getSwitchUnitsSpeed() {
        return switchUnitsSpeed;
    }

    public void setSwitchUnitsSpeed(boolean switchUnitsSpeed) {
        this.switchUnitsSpeed = switchUnitsSpeed;
    }

    public void setImageResponse(String imageResponse) {
        this.imageResponse = imageResponse;
    }

    public String getImageResponse() {
        return imageResponse;
    }

    public boolean getSwitchUnitsPres() {
        return switchUnitsPres;
    }

    public void setSwitchUnitsPres(boolean switchUnitsPres) {
        this.switchUnitsPres = switchUnitsPres;
    }

    public static Singleton getSingleton(){
            if(instance == null){
                instance = new Singleton();
        }
        return instance;
    }

    public void setCityForRequest(String cityForRequest) {
        this.cityForRequest = cityForRequest;
    }

    public String getCityForRequest() {
        return cityForRequest;
    }
}
