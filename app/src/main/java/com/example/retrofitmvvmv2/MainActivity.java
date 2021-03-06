package com.example.retrofitmvvmv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private HeroesAdapter heroesAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HeroesViewModel heroesViewModel;
    public static String TOAST_MESSAGE = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(view -> refreshHeroesList());

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        heroesAdapter = new HeroesAdapter(MainActivity.this);
        recyclerView.setAdapter(heroesAdapter);

        heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        heroesViewModel.getHeroesList().observe(this, heroes -> heroesAdapter.setHeroesList(heroes));

        heroesViewModel.getIsUpdating().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void refreshHeroesList() {
        heroesViewModel.refreshHeroesList();
    }

}
