package com.gabrielaangebrandt.funworld.picado_activity;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public interface PicadoContract {
    interface PicadoView {}

    interface PicadoPresenter{
        void onStart();
        void onStop();
    }
}
