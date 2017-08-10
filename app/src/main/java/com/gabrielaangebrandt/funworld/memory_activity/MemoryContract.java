package com.gabrielaangebrandt.funworld.memory_activity;

import com.gabrielaangebrandt.funworld.models.data_model.MemoryObject;

import java.util.List;

/**
 * Created by Gabriela on 27.7.2017..
 */

public interface MemoryContract {
    interface MemoryView{
        void getDefinedDrawables(List<String> definedDrawables);

        void sendTimeData(String format);
    }

    interface MemoryPresenter{
        void onStart();
        void onStop();
    }
}
