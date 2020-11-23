package com.example.version2myrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.viewModels.MainViewModel;
import com.example.version2myrecipe.views.FragmentCalendar;
import com.example.version2myrecipe.views.FragmentGrocery;
import com.example.version2myrecipe.views.FragmentRandom;
import com.example.version2myrecipe.views.FragmentTags;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private TextView toolbarTitle;
    private Toolbar toolbar;
    private MainViewModel viewModel;
    private FragmentTransaction ft;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.init();

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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("trying to pop stack");
                getSupportFragmentManager().popBackStackImmediate();
            }
        });


        //Bottom navigation button
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        //For backstack fragment management
        ft = getSupportFragmentManager().beginTransaction();
        fm = getSupportFragmentManager();

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                System.out.println("Kaka");
            }
        });

        //Set Default fragment
        toolbarTitle.setText("Recipes");
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
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.fragment_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}