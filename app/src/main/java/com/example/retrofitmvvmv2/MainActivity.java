package com.example.retrofitmvvmv2;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HeroesAdapter heroesAdapter;
    private RecyclerView recyclerView;
    private ProgressBar  progressBar;
    private HeroesViewModel heroesViewModel;
    public static String  TOAST_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(view -> startObserve());

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        heroesViewModel.getHeroesList().observe(this, heroes -> {
            heroesAdapter = new HeroesAdapter(MainActivity.this, heroes);
            recyclerView.setAdapter(heroesAdapter);
        });

        heroesViewModel.getIsUpdating().observe(this, aBoolean -> {
            if (aBoolean){
                progressBar.setVisibility(View.VISIBLE);
            }else {
                Toast.makeText(this, TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void startObserve() {
        heroesViewModel.refreshHeroesList();
    }


}
