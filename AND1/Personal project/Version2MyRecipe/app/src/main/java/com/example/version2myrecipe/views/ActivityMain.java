package com.example.version2myrecipe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.viewModels.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView toolbarTitle;
    Toolbar toolbar;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.init();

        //Toolbar

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                toolbarTitle.setText("Recipes");
                fragment = new FragmentTags(getSupportFragmentManager(), toolbarTitle, getSupportActionBar());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Recipes");

        //Bottom navigation button
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        //Set Default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentTags(getSupportFragmentManager(), toolbarTitle, getSupportActionBar())).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.calendar:
                            toolbarTitle.setText("Calendar");
                            fragment = new FragmentCalendar();
                            break;
                        case R.id.recipes:
                            toolbarTitle.setText("Recipes");
                            fragment = new FragmentTags(getSupportFragmentManager(), toolbarTitle, getSupportActionBar());
                            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            break;
                        case R.id.grocery:
                            toolbarTitle.setText("Grocery");
                            fragment = new FragmentGrocery();
                            break;
                        case R.id.random:
                            toolbarTitle.setText("Random");
                            fragment = new FragmentRandom();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}