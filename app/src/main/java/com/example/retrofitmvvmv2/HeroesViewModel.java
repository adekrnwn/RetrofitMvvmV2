package com.example.retrofitmvvmv2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

class HeroesViewModel extends AndroidViewModel {

    private HeroesRepository heroesRepository;
    private LiveData<List<Hero>> heroesList;

    @Inject
    public HeroesViewModel(Application application){
        super(application);
        heroesRepository = new HeroesRepository();
        heroesList = heroesRepository.getHeroesList();
    }

    LiveData<List<Hero>> getHeroesList() {
        return heroesList;
    }

    LiveData<Boolean>getIsUpdating(){
        return heroesRepository.getIsUpdating();
    }

    void refreshHeroesList(){
        heroesList = heroesRepository.getHeroesList();
    }

}
