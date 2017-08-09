package com.gabrielaangebrandt.funworld.memory_activity.presenter;

import android.util.Log;

import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Gabriela on 27.7.2017..
 */

public class MemoryPresenterImpl implements MemoryContract.MemoryPresenter {
    private MemoryContract.MemoryView view;
    private List<Integer> numbers = new ArrayList<>();
    private List<String> definedDrawables = new ArrayList<>();
    private List<String> drawables;
    Random random = new Random();

    public MemoryPresenterImpl(MemoryContract.MemoryView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        drawables = Arrays.asList(
                "al", "am", "ad", "at", "az", "ba", "bg", "be", "by", "ch", "cy",
                "cz", "dk", "de", "fi", "fr", "gr", "gb", "ge",
                "hr", "hu", "ie", "is", "it", "ee", "xk", "es",
                "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
                "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
                "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");

        definedDrawables.clear();
        numbers.clear();
        for(int i= 0; i<52; i++){
            numbers.add(i);
            Log.d("tag", "ovo je " + i);
        }
        Collections.shuffle(numbers);
        for (int k = 0; k < 9; k++) {
            definedDrawables.add(drawables.get(numbers.get(k)));
            definedDrawables.add(drawables.get(numbers.get(k)));
        }
        Collections.shuffle(definedDrawables);
        view.getDefinedDrawables(definedDrawables);

        //  compositeDisposable.add();


    }

    @Override
    public void onStop() {
    }

}