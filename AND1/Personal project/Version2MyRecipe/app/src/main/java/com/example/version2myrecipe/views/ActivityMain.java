package com.example.version2myrecipe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.views.FragmentCalendar;
import com.example.version2myrecipe.views.FragmentGrocery;
import com.example.version2myrecipe.views.FragmentTags;
import com.example.version2myrecipe.adapter.TagAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity implements TagAdapter.OnListItemClickListener {
    BottomNavigationView bottomNavigationView;
    TextView toolbarTitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentTags()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.calendar:
                            fragment = new FragmentCalendar();
                            toolbarTitle.setText("Calendar");
                            break;
                        case R.id.recipes:
                            fragment = new FragmentTags();
                            toolbarTitle.setText("Recipes");
                            break;
                        case R.id.grocery:
                            fragment = new FragmentGrocery();
                            toolbarTitle.setText("Grocery");
                            break;
                        case R.id.random:
                            fragment = new FragmentRandom();
                            toolbarTitle.setText("Random");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                }
            };

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), ""+ position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}