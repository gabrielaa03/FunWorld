package com.gabrielaangebrandt.funworld.base;

import io.reactivex.disposables.Disposable;

public interface Base {
    void addObserver(Disposable disposable);

    void disposeCompositeD();
}
