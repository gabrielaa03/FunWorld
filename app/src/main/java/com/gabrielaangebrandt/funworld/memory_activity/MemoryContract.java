package com.gabrielaangebrandt.funworld.memory_activity;

import java.util.List;

/**
 * Created by Gabriela on 27.7.2017..
 */

public interface MemoryContract {
    interface MemoryView{

        void getDefinedDrawables(List<String> definedDrawables);
    }

    interface MemoryPresenter{
        void onStart();
        void onStop();
    }
}
