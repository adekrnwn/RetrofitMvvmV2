package com.example.retrofitmvvmv2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroApiInterface {

    @GET("marvel")
    Call<List<Hero>> getHeroes();

}
