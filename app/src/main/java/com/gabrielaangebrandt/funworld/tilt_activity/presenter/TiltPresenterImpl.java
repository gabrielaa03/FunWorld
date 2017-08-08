package com.gabrielaangebrandt.funworld.tilt_activity.presenter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.models.CountryInteractorImpl;
import com.gabrielaangebrandt.funworld.models.interactors.CountryInteractor;
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
    String top = "";
    String right = "";
    String left = "";
    Random random = new Random();
    int counterFalse = 0 , counterTrue = 0;

    List<String> drawables = Arrays.asList(
            "al", "am", "ad", "at", "az", "ba", "bg", "be", "by", "ch", "cy",
            "cz", "dk", "de", "fi", "fr", "gr", "gb", "gs",
            "hr", "hu", "ie", "is", "it", "ee", "xk", "es",
            "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
            "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
            "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");

    public TiltPresenterImpl(TiltContract.TiltView view) {
        this.view = view;
        interactor = new CountryInteractorImpl();
    }

    @Override
    public void onStart() {
        int number1;
        int number2;
        putIntoHashMap();
        do {
            number1 = random.nextInt(52);
            number2 = random.nextInt(52);
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
    public void checkAnswer(Context ctx, String side, String nameFlag) {
        String key = getKey(nameFlag);
        switch (side) {
            case "leftFlag":
                Animation animation;
                assert key != null;
                if (key.equals(left)) {
                    animation = AnimationUtils.loadAnimation(ctx, R.anim.zoom_in_flag);
                    counterTrue++;

                } else {
                    animation = AnimationUtils.loadAnimation(ctx, R.anim.zoom_out);
                    counterFalse++;
                }
                view.sendAnimation("left", animation, counterFalse, counterTrue);
                break;
            case "rightFlag":
                Animation animation1;
                assert key != null;
                if (key.equals(right)) {
                    animation1 = AnimationUtils.loadAnimation(ctx, R.anim.zoom_in_flag);
                    counterTrue++;
                } else {
                    animation1 = AnimationUtils.loadAnimation(ctx, R.anim.zoom_out);
                    counterFalse++;
                }
                view.sendAnimation("right", animation1, counterFalse, counterTrue);
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
        hashmap.put("hr", "Croatia");
        hashmap.put("cy", "Cyprus");
        hashmap.put("cz", "Czech Republic");
        hashmap.put("dk", "Denmark");
        hashmap.put("de", "Germany");
        hashmap.put("fi", "Finland");
        hashmap.put("fr", "France");
        hashmap.put("gr", "Greece");
        hashmap.put("gb", "United Kingdom");
        hashmap.put("ge", "Georgia");
        hashmap.put("ee", "Estonia");
        hashmap.put("hu", "Hungary");
        hashmap.put("is", "Iceland");
        hashmap.put("ie", "Ireland");
        hashmap.put("it", "Italy");
        hashmap.put("kz", "Kazakhstan");
        hashmap.put("xk", "Kosovo");
        hashmap.put("lv", "Latvia");
        hashmap.put("li", "Liechtenstein");
        hashmap.put("lt", "Lithuania");
        hashmap.put("lt", "Luxembourg");
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
        hashmap.put("sm", "San Marino");
        hashmap.put("rs", "Serbia");
        hashmap.put("sk", "Slovakia");
        hashmap.put("si", "Slovenia");
        hashmap.put("es", "Spain");
        hashmap.put("se", "Sweden");
        hashmap.put("tr", "Turkey");
        hashmap.put("ua", "Ukraine");
        hashmap.put("va", "Vatican");
    }
}
