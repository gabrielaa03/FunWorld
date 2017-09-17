package com.gabrielaangebrandt.funworld.one_coutry_activity.presenter;

import android.util.Log;

import com.gabrielaangebrandt.funworld.one_coutry_activity.CountryContract;

import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.CountryInteractorImpl;
import com.gabrielaangebrandt.funworld.models.data_model.Currency;
import com.gabrielaangebrandt.funworld.models.data_model.Example;
import com.gabrielaangebrandt.funworld.models.data_model.Language;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.CountryInteractor;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.listeners.Listener;

import java.util.ArrayList;
import java.util.List;

public class CountryPresenterImpl implements CountryContract.CountryPresenter, Listener {

    private CountryContract.CountryView view;
    private CountryInteractor interactor;
    private String nameOfCountry = "";

    public CountryPresenterImpl(CountryContract.CountryView view) {
        this.view = view;
        interactor = new CountryInteractorImpl();
    }

    @Override
    public void onSuccess(List<Example> list) {

        String name = "";
        Integer population = null;
        Double area = null;
        String region = "";
        String alphaCode3 = "";
        String alphaCode2 = "";
        String capital = "";
        List<Language> languageList = new ArrayList<>();
        List<Currency> currencyList = new ArrayList<>();
        String language = "";
        String currency = "";

        for (int i = 0; i < list.size(); i++) {
            name = list.get(i).getName();
            population = list.get(i).getPopulation();
            area = list.get(i).getArea();
            region = list.get(i).getRegion();
            alphaCode3 = list.get(i).getAlpha3Code();
            alphaCode2 = list.get(i).getAlpha2Code();
            capital = list.get(i).getCapital();
            languageList = list.get(i).getLanguages();
            currencyList = list.get(i).getCurrencies();
        }

        for (int i = 0; i < languageList.size(); i++) {
            language = languageList.get(i).getName();
        }

        for (int i = 0; i < currencyList.size(); i++) {
            currency = currencyList.get(i).getName();
        }


        view.sendData(name, population, area, region, alphaCode3, alphaCode2, capital, language, currency);

    }

    @Override
    public void onError() {
        Log.d("error", "Cannot get response.");
    }

    @Override
    public void getName(String name) {
        nameOfCountry = name;
    }

    @Override
    public void onStart() {
        interactor.checkDisposable(nameOfCountry, this);
    }

    @Override
    public void onStop() {
        interactor.disposeCompositeD();
    }
}


