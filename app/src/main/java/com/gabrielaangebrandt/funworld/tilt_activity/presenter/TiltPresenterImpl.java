package com.gabrielaangebrandt.funworld.tilt_activity.presenter;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.CountryInteractorImpl;
import com.gabrielaangebrandt.funworld.models.interactors.country_interactor.CountryInteractor;
import com.gabrielaangebrandt.funworld.tilt_activity.TiltContract;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Plava tvornica on 28.7.2017..
 */

public class TiltPresenterImpl implements TiltContract.TiltPresenter {
    private TiltContract.TiltView view;
    private CountryInteractor interactor;
    private Map<String, String> hashmap = new HashMap<>();
    private String top = "";
    private String right = "";
    private String left = "";
    private Random random = new Random();
    private int counterFalse = 0, counterTrue = 0;
    boolean soundLoaded;

    List<String> drawables = Arrays.asList(
            "al", "am", "ad", "at", "az", "ba", "bg", "be", "by", "ch", "cy",
            "cz", "dk", "de", "fi", "fr", "gr", "gb", "ge",
            "hr", "hu", "ie", "is", "it", "ee", "xk", "es",
            "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
            "mk", "mt", "nl", "no", "ro", "rs", "ru", "pl",
            "pt", "se", "si", "sk", "sm", "tr", "ua", "va");

    public TiltPresenterImpl(TiltContract.TiltView view) {
        this.view = view;
        interactor = new CountryInteractorImpl();
    }

    @Override
    public void onStart() {
        if (counterFalse + counterTrue >= 20) {
            counterFalse = 0;
            counterTrue = 0;
        }
        int number1;
        int number2;
        putIntoHashMap();
        do {
            number1 = random.nextInt(51);
            number2 = random.nextInt(51);
        } while (number1 == number2);

        left = drawables.get(number1);
        right = drawables.get(number2);
        int num = random.nextInt(2);
        if (num == 0) {
            top = hashmap.get(left);
        } else {
            top = hashmap.get(right);
        }
        view.sendNumbers(left, right, top);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void checkAnswer(String side, String nameFlag) {
        String key = getKey(nameFlag);
        switch (side) {
            case "leftFlag":
                assert key != null;
                if (key.equals(left)) {
                    view.playSound(2);
                } else {
                    view.playSound(1);
                    counterFalse++;
                }

                view.sendAction(counterFalse, counterTrue);

                break;

            case "rightFlag":
                assert key != null;
                if (key.equals(right)) {
                    view.playSound(2);
                    counterTrue++;
                } else {
                    view.playSound(1);
                    counterFalse++;
                }

                view.sendAction(counterFalse, counterTrue);
                break;
        }
    }

    private String getKey(String nameFlag) {
        for (String s : hashmap.keySet()) {
            if (hashmap.get(s).equals(nameFlag)) {
                return s;
            }
        }
        return null;
    }


    private void putIntoHashMap() {
        hashmap.put("al", "Albania");
        hashmap.put("am", "Armenia");
        hashmap.put("ad", "Andorra");
        hashmap.put("at", "Austria");
        hashmap.put("az", "Azerbaijan");
        hashmap.put("ba", "Bosnia and Herzegovina");
        hashmap.put("bg", "Bulgaria");
        hashmap.put("by", "Belarus");
        hashmap.put("be", "Belgium");
        hashmap.put("ch", "Switzerland");
        hashmap.put("cy", "Cyprus");
        hashmap.put("cz", "Czech Republic");
        hashmap.put("dk", "Denmark");
        hashmap.put("de", "Germany");
        hashmap.put("ee", "Estonia");
        hashmap.put("es", "Spain");
        hashmap.put("fi", "Finland");
        hashmap.put("fr", "France");
        hashmap.put("gr", "Greece");
        hashmap.put("gb", "United Kingdom");
        hashmap.put("ge", "Georgia");
        hashmap.put("hr", "Croatia");
        hashmap.put("hu", "Hungary");
        hashmap.put("is", "Iceland");
        hashmap.put("ie", "Ireland");
        hashmap.put("it", "Italy");
        hashmap.put("kz", "Kazakhstan");
        hashmap.put("xk", "Kosovo");
        hashmap.put("lv", "Latvia");
        hashmap.put("li", "Liechtenstein");
        hashmap.put("lt", "Lithuania");
        hashmap.put("lu", "Luxembourg");
        hashmap.put("mk", "Macedonia");
        hashmap.put("mt", "Malta");
        hashmap.put("md", "Moldova");
        hashmap.put("mc", "Monaco");
        hashmap.put("me", "Montenegro");
        hashmap.put("nl", "Netherlands");
        hashmap.put("no", "Norway");
        hashmap.put("pl", "Poland");
        hashmap.put("pt", "Portugal");
        hashmap.put("ro", "Romania");
        hashmap.put("ru", "Russia");
        hashmap.put("rs", "Serbia");
        hashmap.put("sm", "San Marino");
        hashmap.put("sk", "Slovakia");
        hashmap.put("si", "Slovenia");
        hashmap.put("se", "Sweden");
        hashmap.put("tr", "Turkey");
        hashmap.put("ua", "Ukraine");
        hashmap.put("va", "Vatican");
    }
}