package com.gabrielaangebrandt.funworld.picado_activity;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface PicadoContract {
    interface PicadoView {
        long sendStartTime();
        void getTime(String format);
        void sendCityName(String country);
        void showScore(double score);
    }

    interface PicadoPresenter{
        void checkIfCoordinatesAreCorrect(String coordinates, String city, long timeInLong);
        String[] showRealCoordinatesOfCity(String cityName);
        void onStart();
        void onStop();
    }
}
