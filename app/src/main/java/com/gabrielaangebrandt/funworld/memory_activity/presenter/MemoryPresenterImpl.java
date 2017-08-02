package com.gabrielaangebrandt.funworld.memory_activity.presenter;

import android.util.Log;

import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Gabriela on 27.7.2017..
 */

public class MemoryPresenterImpl implements MemoryContract.MemoryPresenter{
    private MemoryContract.MemoryView view;
    List<String> numbers = new ArrayList<>();
    List<String>  definedDrawables;
    List<String> drawables;

    public MemoryPresenterImpl(MemoryContract.MemoryView view) {
        this.view = view;
    }

    @Override
    public void onStart() {

        drawables = Arrays.asList("al", "am", "ad", "at", "az", "ba", "ch", "cy",
                "cz", "dk", "de","fi", "fr", "gr", "gb", "gs",
                "b", "be", "by", "hr", "hu", "ie", "is", "it",
                "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
                "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
                "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");
        definedDrawables = new ArrayList<>();
        for (int k=0; k<8; k++) {
            Random random = new Random();
            int number1 = random.nextInt(50);
            numbers.add(String.valueOf(number1));
           /* for(int i=0;i<numbers.size();i++){
                if(String.valueOf(number1) == numbers.get(i)){
                    k--;
                    break;
                }
            }*/
            definedDrawables.add(drawables.get(number1));
            Log.d("error", "ovo je br : " + number1);
        }
        view.getDefinedDrawables(definedDrawables);
    }

    @Override
    public void onStop() {

    }

}
