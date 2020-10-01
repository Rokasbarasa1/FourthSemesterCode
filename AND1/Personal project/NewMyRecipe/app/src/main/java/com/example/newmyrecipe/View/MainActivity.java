package com.example.newmyrecipe.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.newmyrecipe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    LinearLayout ingredientsList; // getting the instance of this crashes the app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe_layout);

        //ingredientsList.findViewById(R.id.ingredientsList);
        /*
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RecipesFragment()).commit();

         */
    }

    public void createNewLineOfIngredient(){ //Running this also crashes the app
        //TODO fix dynam add code
        //ConstraintLayout ingredientView = findViewById(R.id.ingredientView);
        //ingredientsList.addView(ingredientView);
    }
/*
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }
            };
     */
}