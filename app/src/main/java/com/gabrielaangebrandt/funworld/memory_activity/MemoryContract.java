package com.gabrielaangebrandt.funworld.memory_activity;

import java.util.List;

public interface MemoryContract {
    interface MemoryView {
        void getDefinedDrawables(List<String> definedDrawables);

        void sendTimeData(String format);

        void showScore();
    }

    interface MemoryPresenter {
        void onStart();

        void onStop();

        void setTimer();
    }
}
