package com.example.version2myrecipe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.version2myrecipe.R;

public class RecipeScreen extends AppCompatActivity {
    private EditText name;
    private EditText prepTime;
    private EditText cookTime;
    private EditText servingSize;
    private LinearLayout ingredientsList;
    private LayoutInflater vi;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        ingredientsList = findViewById(R.id.ingredientsList);
        vi = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        v = getLayoutInflater().inflate(R.layout.new_tag_layout, null);
    }

    public void createNewLineOfIngredient(View v){ //Running this also crashes the app
        if(v.getParent() != null) {
            ((ViewGroup)v.getParent()).removeView(v);
        }
        ingredientsList.addView(v, 0);
    }
}