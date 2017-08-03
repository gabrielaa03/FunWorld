package com.gabrielaangebrandt.funworld.memory_activity.presenter;

import android.util.Log;

import com.gabrielaangebrandt.funworld.memory_activity.MemoryContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gabriela on 27.7.2017..
 */

public class MemoryPresenterImpl implements MemoryContract.MemoryPresenter {
    private MemoryContract.MemoryView view;
    private List<String> numbers = new ArrayList<>();
    private List<String> definedDrawables = new ArrayList<>();
    private List<String> drawables, doubledDrawables;

    public MemoryPresenterImpl(MemoryContract.MemoryView view) {
        this.view = view;
    }
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public void onStart() {

        drawables = Arrays.asList("al", "am", "ad", "at", "az", "ba", "ch", "cy",
                "cz", "dk", "de", "fi", "fr", "gr", "gb", "gs",
                "bg", "be", "by", "hr", "hu", "ie", "is", "it",
                "kz", "li", "lt", "lu", "lv", "md", "mc", "me",
                "mk", "mt", "nl", "no", "ro", "pl", "pt", "ro",
                "rs", "ru", "se", "si", "sk", "sm", "tr", "ua", "va");
        definedDrawables.clear();
        numbers.clear();
        int number = 50;
        for (int k = 0; k < 9; k++) {
            Random random = new Random();

            int number1 = random.nextInt(number);
            numbers.add(String.valueOf(number1));
            definedDrawables.add(drawables.get(number1));
            definedDrawables.add(drawables.get(number1));
            Log.d("error", "ovo je br : " + number1);
        }
        Collections.shuffle(definedDrawables);
        view.getDefinedDrawables(definedDrawables);

        compositeDisposable.add(
                Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void onStop() {}

}