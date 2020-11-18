package com.example.version2myrecipe.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.EmptyIngredientAdapter;
import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.viewModels.CreateRecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreateRecipe extends AppCompatActivity implements  EmptyIngredientAdapter.OnEditTextListener{
    EditText name;
    EditText prepTime;
    EditText cookTime;
    EditText servingSize;
    MultiAutoCompleteTextView description;
    MultiAutoCompleteTextView tags;
    RecyclerView ingredientList;
    EmptyIngredientAdapter ingredientAdapter;
    List<Ingredient> ingredients;
    CreateRecipeViewModel createRecipeViewModel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        createRecipeViewModel = ViewModelProviders.of(this).get(CreateRecipeViewModel.class);
        createRecipeViewModel.init();

        //Recycler view
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredientList = findViewById(R.id.rv_ingredients);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ingredientAdapter = new EmptyIngredientAdapter(ingredients, this);
        ingredientList.setAdapter(ingredientAdapter);

    }

    public void createNewLineOfIngredient(View v){
        ingredients.add(new Ingredient());
        ingredientAdapter.notifyItemInserted(ingredients.size() - 1);
    }

    public void finishRecipe(View view) {
        name = findViewById(R.id.newRecipeName);
        prepTime = findViewById(R.id.newRecipePrepTime);
        cookTime = findViewById(R.id.newRecipeCookTime);
        servingSize = findViewById(R.id.newRecipeServingSize);
        description = findViewById(R.id.newRecipeDescription);
        tags = findViewById(R.id.newRecipeTags);
        Log.d("RecipeFinish", "Name " + name.getText() +  " " + prepTime.getText().toString() +  " " + cookTime.getText().toString() +  " " + servingSize.getText().toString() +  " " + description.getText().toString() +  " " + tags.getText().toString());
        createRecipeViewModel.addRecipe(name.getText().toString(), prepTime.getText().toString(), cookTime.getText().toString(), servingSize.getText().toString(), description.getText().toString(), tags.getText().toString());
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
    public void onEdit(int position, String text) {
        System.out.println(position+ text);
        createRecipeViewModel.ingredientUpdated(position, text);
        Ingredient selected = ingredients.get(position);
        selected.setRaw(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}