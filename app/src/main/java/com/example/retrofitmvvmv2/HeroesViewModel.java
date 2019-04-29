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

    public HeroesViewModel(Application application){
        super(application);
        heroesRepository = new HeroesRepository(application);

    }

    LiveData<List<Hero>> getHeroesList() {
        return heroesRepository.getHeroesList();
    }

    LiveData<Boolean>getIsUpdating(){
        return heroesRepository.getIsUpdating();
    }

}
