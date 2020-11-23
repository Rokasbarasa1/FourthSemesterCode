package com.example.version2myrecipe.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.AdapterSeeRecipeIngredient;
import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.viewModels.SeeRecipeViewModel;

import java.util.List;

public class FragmentSeeRecipe extends Fragment {
    private Recipe selectedRecipe;
    private TextView name;
    private TextView prepTime;
    private TextView cookTime;
    private TextView servingSize;
    private TextView description;
    private TextView tags;
    private RecyclerView ingredientList;
    private AdapterSeeRecipeIngredient ingredientAdapter;
    private List<Ingredient> ingredients;
    private SeeRecipeViewModel viewModel;
    private View rootView;
    private String nameOfRecipe;


    public FragmentSeeRecipe(String recipe) {
        nameOfRecipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_see_recipe, container, false);

        viewModel = ViewModelProviders.of(this).get(SeeRecipeViewModel.class);
        viewModel.init(nameOfRecipe);
        selectedRecipe = viewModel.getRecipe();

        name = rootView.findViewById(R.id.recipeName);
        prepTime = rootView.findViewById(R.id.recipePrepTime);
        cookTime = rootView.findViewById(R.id.recipeCookTime);
        servingSize = rootView.findViewById(R.id.recipeServingSize);
        description = rootView.findViewById(R.id.recipeDescription);
        tags = rootView.findViewById(R.id.recipeTags);

        name.setText(selectedRecipe.getName());
        prepTime.setText(selectedRecipe.getPrepTime() + " min");
        cookTime.setText(selectedRecipe.getCookTime() + " min");
        servingSize.setText(selectedRecipe.getServingSize() + " servings");
        description.setText(selectedRecipe.getDescription() + "");
        String recipeTags = "";
        for (int i = 0; i < selectedRecipe.getTags().size(); i++) {
            recipeTags +=  selectedRecipe.getTags().get(i).getName();
            if(i < selectedRecipe.getTags().size()-1)
                recipeTags += ",";
        }
        tags.setText(recipeTags);

        ingredients = selectedRecipe.getIngredients();
        ingredientList = rootView.findViewById(R.id.rv_see_recipe_ingredients);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ingredientAdapter = new AdapterSeeRecipeIngredient(ingredients);
        ingredientList.setAdapter(ingredientAdapter);

        return rootView;
    }

    public void addCalendar(View view) {

    }

    public void deleteRecipe(View view) {
    }

    public void addGrocery(View view) {

    }
}