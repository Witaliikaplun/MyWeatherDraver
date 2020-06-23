package com.example.myweatherdraver.db;

import android.util.Log;

import com.example.myweatherdraver.list_elements.CityFavourites;

import java.util.List;

//Класс CityFavSourceForDB будет связующим звеном между библиотекой Room и RecyclerView
public class CityFavSourceForDB {
    private final ICityFavDAO iCityFavDAO;
    // Буфер с данными: сюда будем подкачивать данные из БД
    private List<CityFavourites> listCity;


    public CityFavSourceForDB(ICityFavDAO iCityFavDAO, List<CityFavourites> listCity) {
        this.iCityFavDAO = iCityFavDAO;
        this.listCity = listCity;
    }

    //получить все города из БД
    public List<CityFavourites> getListCity(){
        // Если объекты еще не загружены, загружаем их.
        // Это сделано для того, чтобы не делать запросы к БД каждый раз
        if ( listCity == null ){
            loadCity();
        }
        return listCity;
    }
    // Загружаем города в буфер
    public void loadCity(){
        listCity = iCityFavDAO.getAllCityFav();
    }

    // Добавляем город
    public void addCity(CityFavourites cityFavourites){
        iCityFavDAO.insertCityFav(cityFavourites);
        // После изменения БД надо повторно прочесть данные из БД и загрузить в буфер
        loadCity();
    }

    // Удаляем город
    public void delCity(CityFavourites cityFavourites){
        iCityFavDAO.deleteCityFav(cityFavourites);
        // После изменения БД надо повторно прочесть данные из БД и загрузить в буфер
        loadCity();
    }





}
