package com.gabrielaangebrandt.funworld.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Plava tvornica on 11.8.2017..
 */

public class BaseImpl implements Base {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addObserver(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed())
            compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    public void disposeCompositeD() {
        compositeDisposable.dispose();
    }
}
