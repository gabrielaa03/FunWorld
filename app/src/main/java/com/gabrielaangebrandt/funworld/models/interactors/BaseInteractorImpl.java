package com.gabrielaangebrandt.funworld.models.interactors;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class BaseInteractorImpl implements BaseInteractor {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void disposeO() {
        if(compositeDisposable != null || compositeDisposable.isDisposed()){
            compositeDisposable = new CompositeDisposable();
        }
        else
            compositeDisposable.dispose();
    }
}
