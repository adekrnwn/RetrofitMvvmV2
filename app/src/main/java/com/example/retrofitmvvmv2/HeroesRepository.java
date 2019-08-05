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

@Singleton
class HeroesRepository {

     private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
     private HeroApiInterface api;
     private MutableLiveData<List<Hero>> heroesList = new MutableLiveData<>();

    public HeroesRepository(){
        api = HeroApiClient.getClient().create(HeroApiInterface.class);
    }

    //This method is using Retrofit to get the JSON data from URL
     LiveData<List<Hero>> getHeroesList() {

        Call<List<Hero>> call = api.getHeroes();

        isUpdating.setValue(true);
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call,@NonNull Response<List<Hero>> response) {

                MainActivity.TOAST_MESSAGE = "Loaded";
                //setting the list to MutableLiveData
                heroesList.setValue(response.body());
                isUpdating.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call,@NonNull Throwable t) {
                MainActivity.TOAST_MESSAGE =t.getMessage();
                isUpdating.setValue(false);
            }
        });
        return heroesList;
    }

    LiveData<Boolean>getIsUpdating(){
         return isUpdating;
    }
}
