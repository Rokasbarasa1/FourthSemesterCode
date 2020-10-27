package com.example.version2myrecipe.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.EmptyIngredientAdapter;
import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.viewModels.CreateRecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreateRecipe extends AppCompatActivity implements  EmptyIngredientAdapter.OnEditTextListener{
    private EditText name;
    private EditText prepTime;
    private EditText cookTime;
    private EditText servingSize;
    private MultiAutoCompleteTextView description;
    private MultiAutoCompleteTextView tags;
    RecyclerView ingredientList;
    EmptyIngredientAdapter ingredientAdapter;
    List<Ingredient> ingredients;
    CreateRecipeViewModel createRecipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        createRecipeViewModel = ViewModelProviders.of(this).get(CreateRecipeViewModel.class);
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
        //createRecipeViewModel.addRecipe(name.getText(), prepTime.getText(), cookTime.getText(), servingSize.getText(), description.getText(), tags.getText());
        // IMPLEMENT THE FUCKING ADD INGREDIENT THING that updates every time you finish typing.
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

    @Override
    public void onEdit(int position) {

    }
}