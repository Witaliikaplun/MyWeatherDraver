package com.example.myweatherdraver.data;

public class DataParameters {
    private static DataParameters instance = null;
    private String temperature_actual;
    private String press_actual;
    private String humi_actual;
    private String windSpeed_actual;
    private String descript_actual;
    private String img_actual;
    private String name;


    private DataParameters(){

    }

    public static DataParameters getInstance(){
        if(instance == null) instance = new DataParameters();
        return instance;
    }

    public void setTemperature_actual(String temperature_actual) {
        this.temperature_actual = temperature_actual;
    }

    public String getTemperature_actual() {
        return temperature_actual;
    }


    public String getPress_actual() {
        return press_actual;
    }

    public void setPress_actual(String press_actual) {
        this.press_actual = press_actual;
    }

    public String getHumi_actual() {
        return humi_actual;
    }

    public void setHumi_actual(String humi_actual) {
        this.humi_actual = humi_actual;
    }

    public String getWindSpeed_actual() {
        return windSpeed_actual;
    }

    public void setWindSpeed_actual(String windSpeed_actual) {
        this.windSpeed_actual = windSpeed_actual;
    }

    public String getDescript_actual() {
        return descript_actual;
    }

    public void setDescript_actual(String descript_actual) {
        this.descript_actual = descript_actual;
    }

    public String getImg_actual() {
        return img_actual;
    }

    public void setImg_actual(String img_actual) {
        this.img_actual = img_actual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
