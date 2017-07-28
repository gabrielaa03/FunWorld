package com.gabrielaangebrandt.funworld.models;


import com.gabrielaangebrandt.funworld.RestUtilst.Utils;
import com.gabrielaangebrandt.funworld.models.data_model.Example;
import com.gabrielaangebrandt.funworld.models.interactors.BaseInteractorImpl;
import com.gabrielaangebrandt.funworld.models.interactors.CountryInteractor;
import com.gabrielaangebrandt.funworld.models.interactors.listeners.Listener;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class CountryInteractorImpl extends BaseInteractorImpl implements CountryInteractor{
    private CompositeDisposable disposable = new CompositeDisposable();

    public void checkDisposable(String name, final Listener listener){
        Observable<List<Example>> feeds = Utils.getApi().getCountry(name);
            disposable.add(feeds.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<Example>>() {
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

}
