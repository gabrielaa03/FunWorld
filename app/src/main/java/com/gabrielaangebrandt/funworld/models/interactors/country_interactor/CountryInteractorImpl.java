package com.gabrielaangebrandt.funworld.models.interactors.country_interactor;


import com.gabrielaangebrandt.funworld.base.BaseImpl;
import com.gabrielaangebrandt.funworld.models.data_model.Example;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.listeners.Listener;
import com.gabrielaangebrandt.funworld.rest_utilst.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class CountryInteractorImpl extends BaseImpl implements CountryInteractor{

    public void checkDisposable(String name, final Listener listener){
            addObserver(getCountryObservable(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<Example>>() {
                @Override
                public void onNext(List<Example> examples) {
                    listener.onSuccess(examples);
                }

                @Override
                public void onError(Throwable e) {
                    listener.onError();
                }

                @Override
                public void onComplete() {

                }
            }));


    }

    @Override
    public Observable<List<Example>> getCountryObservable(String name) {
        return Utils.getApi().getCountry(name);
    }
}
