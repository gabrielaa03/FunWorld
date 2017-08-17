package com.gabrielaangebrandt.funworld.models.interactors.country_interactor;

import com.gabrielaangebrandt.funworld.base.Base;
import com.gabrielaangebrandt.funworld.models.data_model.Example;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.listeners.Listener;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface CountryInteractor extends Base {
    void checkDisposable(String name, Listener listener);

    Observable<List<Example>> getCountryObservable(String name);
}
