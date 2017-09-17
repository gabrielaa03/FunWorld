package com.gabrielaangebrandt.funworld.one_coutry_activity;


public interface CountryContract {
    interface CountryView {
        void sendData(String name, Integer population, Double area, String region, String alphaCode2, String alphaCode3, String capital, String language, String currency);
    }

    interface CountryPresenter {
        void getName(String name);

        void onStart();

        void onStop();
    }

}
