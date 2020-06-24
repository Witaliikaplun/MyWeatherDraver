package com.example.myweatherdraver.data;


import android.os.Handler;

public class DataConversion {
    public static final double PRESS_FACTOR = 1.33322;
    public static final double TEMP_FACTOR = 1.8;
    public static final double SPEED_FACTOR = 3.6;
    private double paramIn;
    private String paramOut;
    private String paramOutThread;
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
            case 2: //преобразование м/с в км/ч
                paramOut = String.format("%.1f", (conwesActivate) ? 3.6 * paramIn : paramIn);
                break;
        }
        return paramOut;
    }

    public String conversionThread() {
        Thread th1;
        th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                switch (mode) {
                    case 0: //преобразованеие мм. рт. ст. в гекто Паскали
                        paramOutThread = String.format("%.1f", (conwesActivate) ? PRESS_FACTOR * paramIn : paramIn);
                        break;
                    case 1: //преобразование град. Цельсия в град Фаренгейты
                        paramOutThread = String.format("%.1f", (conwesActivate) ? TEMP_FACTOR * paramIn + 32.0 : paramIn);
                        break;
                    case 2: //преобразование м/с в км/ч
                        paramOutThread = String.format("%.1f", (conwesActivate) ? SPEED_FACTOR * paramIn : paramIn);
                        break;
                }
            }
        });
        th1.start();
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paramOutThread;
    }
}
