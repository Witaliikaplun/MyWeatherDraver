package com.example.myweatherdraver.data;


import android.os.Handler;

public class DataConversion {
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
                        paramOutThread = String.format("%.1f", (conwesActivate) ? 1.33322 * paramIn : paramIn);
                        break;
                    case 1: //преобразование град. Цельсия в град Фаренгейты
                        paramOutThread = String.format("%.1f", (conwesActivate) ? 1.8 * paramIn + 32.0 : paramIn);
                        break;
                    case 2: //преобразование м/с в км/ч
                        paramOutThread = String.format("%.1f", (conwesActivate) ? 3.6 * paramIn : paramIn);
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
