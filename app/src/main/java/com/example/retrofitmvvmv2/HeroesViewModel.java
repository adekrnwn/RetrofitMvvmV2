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

    private HeroesRepository heroesRepository;
    private MutableLiveData<List<Hero>> heroesList;

    public HeroesViewModel(Application application){
        super(application);
        heroesRepository = new HeroesRepository(application);
        if (heroesList != null){
            return;
        }
        heroesList = heroesRepository.getHeroesList();
    }

    LiveData<List<Hero>> getHeroesList() {
        return heroesList;
    }

    LiveData<Boolean>getIsUpdating(){
        return heroesRepository.getIsUpdating();
    }

    LiveData<List<Hero>>refreshHeroesList(){
        heroesList = heroesRepository.refreshHeroesList();
        return heroesList;
    }

}
