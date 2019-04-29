package com.example.retrofitmvvmv2;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
class HeroesRepository {

    private Application application;

    HeroesRepository(Application application){
        this.application = application;
    }

    //This method is using Retrofit to get the JSON data from URL
    MutableLiveData<List<Hero>> getHeroesList() {

        final MutableLiveData<List<Hero>> heroesList = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call,@NonNull Response<List<Hero>> response) {

                //wrong implementation of toast
                Toast.makeText(application, "Loaded", Toast.LENGTH_SHORT).show();
                //setting the list to MutableLiveData
                heroesList.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call,@NonNull Throwable t) {
                
            }
        });
        return heroesList;
    }
}
