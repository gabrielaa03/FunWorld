package com.gabrielaangebrandt.funworld.models.interactors.country_interactor.listeners;

import com.gabrielaangebrandt.funworld.models.data_model.Example;

import java.util.List;

public interface Listener {
        void onSuccess(List<Example> list);
        void onError();
}
