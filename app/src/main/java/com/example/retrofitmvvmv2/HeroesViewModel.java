package com.example.retrofitmvvmv2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

class HeroesViewModel extends AndroidViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Hero>> heroesList;
    private HeroesRepository heroesRepository;

    @Inject
    public HeroesViewModel(@NonNull Application application){
        super(application);
        heroesRepository = new HeroesRepository(application);
        heroesList = heroesRepository.getHeroesList();
    }

    LiveData<List<Hero>> getHeroesList() {
        return this.heroesList;
    }

}
