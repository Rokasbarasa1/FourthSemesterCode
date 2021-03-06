package com.example.newmyrecipe.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newmyrecipe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private AnimationUtils animations = new AnimationUtils();
    //private BottomNavigationView bottomNavigationView;
    private LinearLayout ingredientsList; // getting the instance of this crashes the app
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;


    private FloatingActionButton add_btn;
    private FloatingActionButton trash_btn;
    private FloatingActionButton create_btn;
    private Button button;
    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.create_recipe_layout);
        button.findViewById(R.id.button1);

        setContentView(R.layout.tag_view_layout);


        add_btn.findViewById(R.id.add_btn);
        trash_btn.findViewById(R.id.trash_btn);
        create_btn.findViewById(R.id.create_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
        trash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked trash button", Toast.LENGTH_SHORT).show();
            }
        });
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked create button", Toast.LENGTH_SHORT).show();
            }
        });

        rotateOpen = animations.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = animations.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = animations.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = animations.loadAnimation(this, R.anim.to_bottom_anim);

        //ingredientsList.findViewById(R.id.ingredientsList);
        /*
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RecipesFragment()).commit();

         */
    }

    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        if(!clicked)
            clicked = true;
        else
            clicked = false;

    }

    @SuppressLint("RestrictedApi")
    private void setVisibility(Boolean clicked) {
        if(!clicked){
            trash_btn.setVisibility(View.VISIBLE);
            create_btn.setVisibility(View.VISIBLE);
        } else {
            trash_btn.setVisibility(View.INVISIBLE);
            create_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean clicked) {
        if(!clicked){
            trash_btn.startAnimation(fromBottom);
            create_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        }else {
            trash_btn.startAnimation(toBottom);
            create_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }

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