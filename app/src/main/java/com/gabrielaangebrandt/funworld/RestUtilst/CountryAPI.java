package com.gabrielaangebrandt.funworld.RestUtilst;


import com.gabrielaangebrandt.funworld.models.data_model.Example;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gabriela on 30.5.2017..
 */

public interface CountryAPI {
    @GET("{name}")
    Observable<List<Example>> getCountry(@Path("name") String name);
}
