package com.gabrielaangebrandt.funworld.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by Plava tvornica on 11.8.2017..
 */

public interface Base {
    void addObserver(Disposable disposable);

    void disposeCompositeD();
}
