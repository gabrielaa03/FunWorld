package com.gabrielaangebrandt.funworld.rest_utilst;


import com.gabrielaangebrandt.funworld.models.data_model.Example;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryAPI {
    @GET("{name}")
    Observable<List<Example>> getCountry(@Path("name") String name);
}
