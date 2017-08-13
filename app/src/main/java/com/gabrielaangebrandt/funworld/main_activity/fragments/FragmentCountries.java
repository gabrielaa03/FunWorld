package com.gabrielaangebrandt.funworld.main_activity.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielaangebrandt.funworld.main_activity.adapters.RecyclerViewAdapterCountries;
import com.gabrielaangebrandt.funworld.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Gabriela on 11.5.2017..
 */

public class



FragmentCountries extends Fragment{
    Unbinder unbinder;
   @BindView(R.id.rv_countries) RecyclerView listView;

    List<String> listOfCountries;
    RecyclerViewAdapterCountries adapter;
    RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_countries_layout, container, false);
        unbinder = ButterKnife.bind(this, v);

        listOfCountries = new ArrayList<>();
        listOfCountries = Arrays.asList(getResources().getStringArray(R.array.countries1));


        layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);


        adapter = new RecyclerViewAdapterCountries(listOfCountries);
        listView.setAdapter(adapter);

        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

