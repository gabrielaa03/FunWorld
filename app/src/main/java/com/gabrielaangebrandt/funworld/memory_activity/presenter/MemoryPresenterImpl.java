package com.gabrielaangebrandt.funworld.memory_activity.presenter;

import com.gabrielaangebrandt.funworld.base.BaseImpl;
import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gabriela on 27.7.2017..
 */

public class MemoryPresenterImpl extends BaseImpl implements MemoryContract.MemoryPresenter {
    private MemoryContract.MemoryView view;
    private List<Integer> numbers = new ArrayList<>();
    private List<String> definedDrawables = new ArrayList<>();
    private List<String> drawables;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    int timer = 0;

    public MemoryPresenterImpl(MemoryContract.MemoryView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        //define list with alphacodes
        drawables = Arrays.asList(
                "al", "am", "ad", "at", "az", "ba", "bg", "be", "by", "ch", "cy",
                "cz", "dk", "de", "fi", "fr", "gr", "gb", "ge",
                "hr", "hu", "ie", "is", "it", "ee", "xk", "es",
                "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
                "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
                "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");

        //clearing cache
        definedDrawables.clear();
        numbers.clear();
        //fill array with 51, shuffle and choose first 8 numbers which will represent indexes in "drawables"
        for (int i = 0; i < 52; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for (int k = 0; k < 9; k++) {
            definedDrawables.add(drawables.get(numbers.get(k)));
            definedDrawables.add(drawables.get(numbers.get(k)));
        }
        Collections.shuffle(definedDrawables);
        view.getDefinedDrawables(definedDrawables);

        //background thread, counting seconds and update after every second
        addObserver(Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                timer += 1000;
                view.sendTimeData(simpleDateFormat.format(timer));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        }));
    }

    //dispose
    @Override
    public void onStop() {
        disposeCompositeD();
    }
    //function to set timer in activity
    @Override
    public void setTimer() {
        timer = 0;
    }
}