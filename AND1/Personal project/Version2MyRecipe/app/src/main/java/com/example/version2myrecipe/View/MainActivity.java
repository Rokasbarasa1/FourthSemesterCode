package com.example.version2myrecipe.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version2myrecipe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TagAdapter.OnListItemClickListener {
    BottomNavigationView bottomNavigationView;
    RecyclerView tagList;
    TagAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler view
        /*
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("kaka"));
        tags.add(new Tag("Mouse"));
        tags.add(new Tag("pet"));
        tags.add(new Tag("Dog"));
        tags.add(new Tag("Cow"));
        tags.add(new Tag("Rat"));
        tags.add(new Tag("Corona"));
        tagList = findViewById(R.id.rv);
        tagList.hasFixedSize();
        tagList.setLayoutManager(new LinearLayoutManager(this));
        tagAdapter = new TagAdapter(tags, this);
        tagList.setAdapter(tagAdapter);
*/
        //Bottom navigation button
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecipesFragment()).commit();



    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.calendar:
                            fragment = new CalendarFragment();
                            break;
                        case R.id.recipes:
                            fragment = new RecipesFragment();
                            break;
                        case R.id.grocery:
                            fragment = new GroceryFragment();
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