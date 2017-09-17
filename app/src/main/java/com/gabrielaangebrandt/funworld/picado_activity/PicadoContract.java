package com.gabrielaangebrandt.funworld.picado_activity;

public interface PicadoContract {
    interface PicadoView {

        void getTime(String format);

        void sendCityName(String country);

        void showScore(double score);
    }

    interface PicadoPresenter {
        void checkIfCoordinatesAreCorrect(String coordinates, String city, long timeInLong);

        String[] showRealCoordinatesOfCity(String cityName);

        void onStart();

        void onStop();
    }
}
