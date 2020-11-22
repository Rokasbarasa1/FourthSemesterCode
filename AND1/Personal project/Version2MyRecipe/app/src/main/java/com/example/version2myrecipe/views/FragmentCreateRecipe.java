package com.example.version2myrecipe.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.AdapterNewRecipeIngredient;
import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.viewModels.CreateRecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentCreateRecipe extends Fragment implements  AdapterNewRecipeIngredient.OnEditTextListener{
    EditText name;
    EditText prepTime;
    EditText cookTime;
    EditText servingSize;
    MultiAutoCompleteTextView description;
    MultiAutoCompleteTextView tags;
    RecyclerView ingredientList;
    AdapterNewRecipeIngredient ingredientAdapter;
    List<Ingredient> ingredients;
    CreateRecipeViewModel createRecipeViewModel;
    Toolbar toolbar;
    View rootView;

    public FragmentCreateRecipe() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_recipe, container, false);

        createRecipeViewModel = ViewModelProviders.of(this).get(CreateRecipeViewModel.class);
        createRecipeViewModel.init();

        //Recycler view
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredientList = rootView.findViewById(R.id.rv_new_recipe_ingredients);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ingredientAdapter = new AdapterNewRecipeIngredient(ingredients, this);
        ingredientList.setAdapter(ingredientAdapter);

        return rootView;
    }

    public void createNewLineOfIngredient(View v){
        ingredients.add(new Ingredient());
        ingredientAdapter.notifyItemInserted(ingredients.size() - 1);
    }

    public void finishRecipe(View view) {
        name = rootView.findViewById(R.id.newRecipeName);
        prepTime = rootView.findViewById(R.id.newRecipePrepTime);
        cookTime = rootView.findViewById(R.id.newRecipeCookTime);
        servingSize = rootView.findViewById(R.id.newRecipeServingSize);
        description = rootView.findViewById(R.id.newRecipeDescription);
        tags = rootView.findViewById(R.id.newRecipeTags);
        Log.d("RecipeFinish", "Name " + name.getText() +  " " + prepTime.getText().toString() +  " " + cookTime.getText().toString() +  " " + servingSize.getText().toString() +  " " + description.getText().toString() +  " " + tags.getText().toString());
        createRecipeViewModel.addRecipe(name.getText().toString(), prepTime.getText().toString(), cookTime.getText().toString(), servingSize.getText().toString(), description.getText().toString(), tags.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("name", 21);
        //finish();
        //TODO FINISH OF ADD
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
}