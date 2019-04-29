package com.example.retrofitmvvmv2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class HeroesRepository {

    private Application application;

    public HeroesRepository(Application application){
        this.application = application;
    }
    //This method is using Retrofit to get the JSON data from URL
    public MutableLiveData<List<Hero>> getHeroesList() {

        final MutableLiveData<List<Hero>> heroesList = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                //wrong implementation of toast
                Toast.makeText(application, "Loaded", Toast.LENGTH_SHORT).show();
                //setting the list to MutableLiveData
                heroesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                
            }
        });
        return heroesList;
    }
}
