package com.example.version2myrecipe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version2myrecipe.R;

public class RecipeScreen extends AppCompatActivity {
    private EditText name;
    private EditText prepTime;
    private EditText cookTime;
    private EditText servingSize;
    private TextView nameOfRecipe;
    private LinearLayout ingredientsList;
    private LayoutInflater vi;
    private ProgressBar progressBar;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        name = findViewById(R.id.name);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookTime);
        servingSize = findViewById(R.id.servingSize);
        progressBar = findViewById(R.id.progressBar);

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

    public void finishRecipe(View view) {
        if(progressBar.getProgress() != 100)
            progressBar.setProgress(progressBar.getProgress() + 10);
        else {
            Intent intent = new Intent();
            intent.putExtra("name", 21);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void goBack(View view) {
        if(progressBar.getProgress() != 0)
            progressBar.setProgress(progressBar.getProgress() - 10);
        else{
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    }
}