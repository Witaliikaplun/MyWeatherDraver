package com.example.myweatherdraver.data;

public class DataConversion {
    private double paramIn;
    private String paramOut;
    private boolean conwesActivate;
    private int mode;
    //0-преобразование давления
    //1-преобразование температуры
    //2-преобразование скорости

    public DataConversion(double paramIn, boolean conwesActivate, int mode) {
        this.paramIn = paramIn;
        this.conwesActivate = conwesActivate;
        this.mode = mode;
    }

    public String conversion() {
        switch (mode) {
            case 0: //преобразованеие мм. рт. ст. в гекто Паскали
                paramOut = String.format("%.1f", (conwesActivate) ? 1.33322 * paramIn : paramIn);
                break;
            case 1: //преобразование град. Цельсия в град Фаренгейты
                paramOut = String.format("%.1f", (conwesActivate) ? 1.8 * paramIn + 32.0 : paramIn);
                break;
            case 2:
                paramOut = String.format("%.1f", (conwesActivate) ? 3.6 * paramIn : paramIn);
                break;
        }

        return paramOut;
    }
}
