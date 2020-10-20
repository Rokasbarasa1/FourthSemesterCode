package com.example.version2myrecipe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version2myrecipe.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeScreen extends AppCompatActivity {
    private EditText name;
    private EditText prepTime;
    private EditText cookTime;
    private EditText servingSize;
    private MultiAutoCompleteTextView description;
    private MultiAutoCompleteTextView tags;
    RecyclerView ingredientList;
    EmptyIngredientAdapter ingredientAdapter;
    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredientList = findViewById(R.id.rv_ingredients);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ingredientAdapter = new EmptyIngredientAdapter(ingredients);
        ingredientList.setAdapter(ingredientAdapter);
    }

    public void createNewLineOfIngredient(View v){
        ingredients.add(new Ingredient());
        ingredientAdapter.notifyItemInserted(ingredients.size() - 1);
    }

    public void finishRecipe(View view) {
        name = findViewById(R.id.name);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookTime);
        servingSize = findViewById(R.id.servingSize);
        description = findViewById(R.id.description);
        tags = findViewById(R.id.tags);
        //viewModel.addRecipe(name, prepTime, cookTime, servingSize, description, tags);

        Intent intent = new Intent();
        intent.putExtra("name", 21);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void removeLastLine(View view) {
        if(ingredients.size() != 0){
            ingredients.remove(ingredients.size()-1);
            ingredientAdapter.notifyDataSetChanged();
        }
    }
}