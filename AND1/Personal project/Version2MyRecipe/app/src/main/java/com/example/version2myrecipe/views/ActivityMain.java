package com.example.version2myrecipe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.views.FragmentCalendar;
import com.example.version2myrecipe.views.FragmentGrocery;
import com.example.version2myrecipe.views.FragmentTags;
import com.example.version2myrecipe.adapter.TagAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity implements TagAdapter.OnListItemClickListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom navigation button
        bottomNavigationView = findViewById(R.id.bottomView);
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
                            break;
                        case R.id.recipes:
                            fragment = new FragmentTags();
                            break;
                        case R.id.grocery:
                            fragment = new FragmentGrocery();
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
}