package com.gabrielaangebrandt.funworld.memory_activity;

/**
 * Created by Gabriela on 27.7.2017..
 */

public interface MemoryContract {
    interface MemoryView{

    }

    interface MemoryPresenter{
        void onStart();
        void onStop();
    }
}
