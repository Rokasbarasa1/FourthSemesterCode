package com.example.version2myrecipe.viewModels;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import java.util.ArrayList;


public class CreateRecipeViewModel extends ViewModel {
    private TagsRepository repo;
    private ArrayList<Ingredient> ingredients;

    public void init(){
        repo = TagsRepository.getInstance();
        ingredients = new ArrayList<>();
    }

    public void addRecipe(String name, String prepTime, String cookTime, String servingSize, String description, String tags){
        ArrayList<Tag> splitTags = new ArrayList<>();
        String[] tagsIndividual = tags.split(",");
        for (int i = 0; i < tagsIndividual.length; i++) {
            splitTags.add(new Tag(tagsIndividual[i]));
        }
        Recipe newRecipe = new Recipe(name, Integer.parseInt(prepTime), Integer.parseInt(cookTime), Integer.parseInt(servingSize), ingredients, description, splitTags);
        repo.addRecipe(newRecipe);
        Log.d("AddedRecipe",  "" + name );
    }

    public void ingredientUpdated(int index, String text) {
        if(!text.equals("")){
            String[] ingredient = text.split(" ");
            int amount = 0;
            int number = 0;
            boolean exists = false;
            for (int i = 0; i < ingredient.length; i++) {
                try{
                    amount = Integer.parseInt(ingredient[i]);
                    number = i;
                }catch(NumberFormatException e){
                }
            }
            text = text.substring(0, text.length() - ingredient[number].length());
            Ingredient newIngredient = new Ingredient(text, amount, "Gram");
            try{
                ingredients.get(index);
                exists = true;
            }catch (IndexOutOfBoundsException e){
            }
            if(exists){
                ingredients.set(index, newIngredient);
            }else {
                ingredients.add(newIngredient);
            }
            // TODO : THIS IS VERY PErformance intensive rather listen to loose focus on editText field.
            Log.d("IngredientUpdated", "updated ingredient " + index );
        }
    }
}
