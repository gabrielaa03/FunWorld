package com.gabrielaangebrandt.funworld.tilt_activity.presenter;

import android.util.Log;

import com.gabrielaangebrandt.funworld.models.CountryInteractorImpl;
import com.gabrielaangebrandt.funworld.models.interactors.CountryInteractor;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;
import com.gabrielaangebrandt.funworld.tilt_activity.view.TiltActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class TiltPresenterImpl implements TiltContract.TiltPresenter {
    TiltContract.TiltView view;
    CountryInteractor interactor;
    List<String> drawables = Arrays.asList("al", "am", "ad", "at", "az", "ba", "ch", "cy",
            "cz", "dk", "de", "fi", "fr", "gr", "gb", "gs",
            "bg", "be", "by", "hr", "hu", "ie", "is", "it",
            "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
            "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
            "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");


    public TiltPresenterImpl(TiltContract.TiltView view) {
        this.view = view;
        interactor = new CountryInteractorImpl();
    }

    @Override
    public void onStart() {
        Random random = new Random();
        int number1 = random.nextInt(50);
        int number2 = random.nextInt(50);
        String left = drawables.get(number1);
        String right = drawables.get(number2);

        String top = "";
        int num = random.nextInt(2);
        if(num == 0){

        }
        view.sendNumbers(left, right, top);
    }

    @Override
    public void onStop() {

    }

}
