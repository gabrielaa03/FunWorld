package com.gabrielaangebrandt.funworld.models.interactors.country_interactor.listeners;

import com.gabrielaangebrandt.funworld.models.data_model.Example;

import java.util.List;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface Listener {
    void onSuccess(List<Example> list);

    void onError();
}
