package com.gabrielaangebrandt.funworld.RestUtilst;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriela on 22.7.2017..
 */

public class Utils {
    public static final String URL = "https://restcountries.eu/rest/v2/name/";

    public static CountryAPI api;

    public static CountryAPI getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL).build();

            api = retrofit.create(CountryAPI.class);
        }
        return api;
    }
}
