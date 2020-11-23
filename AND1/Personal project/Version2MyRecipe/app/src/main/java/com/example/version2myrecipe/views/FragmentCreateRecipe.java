package com.example.version2myrecipe.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.AdapterNewRecipeIngredient;
import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.viewModels.CreateRecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentCreateRecipe extends Fragment implements  AdapterNewRecipeIngredient.OnEditTextListener{
    private EditText name;
    private EditText prepTime;
    private EditText cookTime;
    private EditText servingSize;
    private MultiAutoCompleteTextView description;
    private MultiAutoCompleteTextView tags;
    private RecyclerView ingredientList;
    private AdapterNewRecipeIngredient ingredientAdapter;
    private List<Ingredient> ingredients;
    private CreateRecipeViewModel viewModel;
    private String currentTag;
    private View rootView;


    public FragmentCreateRecipe(String tagUserWasIn) {
        if(tagUserWasIn != null)
            currentTag = tagUserWasIn + ",";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_recipe, container, false);

        viewModel = ViewModelProviders.of(this).get(CreateRecipeViewModel.class);
        viewModel.init();
        Button removeButton = rootView.findViewById(R.id.remove_ingredient_button);
        Button addButton = rootView.findViewById(R.id.add_ingredient_button);
        Button finishButton = rootView.findViewById(R.id.finish_ingredient_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastLine();

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewLineOfIngredient();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishRecipe();
            }
        });

        //Recycler view
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredientList = rootView.findViewById(R.id.rv_new_recipe_ingredients);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ingredientAdapter = new AdapterNewRecipeIngredient(ingredients, this);
        ingredientList.setAdapter(ingredientAdapter);

        tags = rootView.findViewById(R.id.newRecipeTags);
        tags.setText(currentTag);
        return rootView;
    }

    public void createNewLineOfIngredient(){
        ingredients.add(new Ingredient());
        ingredientAdapter.notifyItemInserted(ingredients.size() - 1);
    }

    public void removeLastLine() {
        if(ingredients.size() != 0){
            ingredients.remove(ingredients.size()-1);
            ingredientAdapter.notifyDataSetChanged();
        }
    }

    public void finishRecipe() {
        name = rootView.findViewById(R.id.newRecipeName);
        prepTime = rootView.findViewById(R.id.newRecipePrepTime);
        cookTime = rootView.findViewById(R.id.newRecipeCookTime);
        servingSize = rootView.findViewById(R.id.newRecipeServingSize);
        description = rootView.findViewById(R.id.newRecipeDescription);
        Log.d("RecipeFinish", "Name " + name.getText() +  " " + prepTime.getText().toString() +  " " + cookTime.getText().toString() +  " " + servingSize.getText().toString() +  " " + description.getText().toString() +  " " + tags.getText().toString());
        viewModel.addRecipe(name.getText().toString(), prepTime.getText().toString(), cookTime.getText().toString(), servingSize.getText().toString(), description.getText().toString(), tags.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("name", 21);

        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().popBackStackImmediate();

        //finish();
        //TODO FINISH OF ADD
    }



    @Override
    public void onEdit(int position, String text) {
        System.out.println(position+ text);
        viewModel.ingredientUpdated(position, text);
        Ingredient selected = ingredients.get(position);
        selected.setRaw(text);
    }
}