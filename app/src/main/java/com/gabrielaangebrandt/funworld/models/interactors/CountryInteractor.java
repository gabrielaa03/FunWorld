package com.gabrielaangebrandt.funworld.models.interactors;

import com.gabrielaangebrandt.funworld.models.interactors.listeners.Listener;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface CountryInteractor  extends  BaseInteractor{
        void checkDisposable(String name, Listener listener);
}
