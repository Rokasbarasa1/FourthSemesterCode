package com.example.version2myrecipe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.viewModels.MainViewModel;
import com.example.version2myrecipe.adapter.TagAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity implements TagAdapter.OnListItemClickListener {
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Bottom navigation button
        bottomNavigationView = findViewById(R.id.bottomView);
        toolbarTitle = findViewById(R.id.toolbar_title);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle.setText("Recipes");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentTags(getSupportFragmentManager(), toolbarTitle)).commit();
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
                            fragment = new FragmentTags(getSupportFragmentManager(), toolbarTitle);

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
    public void onClick(int position) {
        Fragment fragment = null;
        fragment = new FragmentTagExpanded(mainViewModel.getTag(position));
        toolbarTitle.setText(mainViewModel.getTag(position).getName());
        //Toast.makeText(getApplicationContext(), ""+ position,Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}