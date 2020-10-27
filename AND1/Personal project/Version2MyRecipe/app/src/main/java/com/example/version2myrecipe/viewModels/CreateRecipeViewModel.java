package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeViewModel extends ViewModel {
    private TagsRepository repo;
    private ArrayList<Ingredient> ingredients;

    public void init(){
        repo = TagsRepository.getInstance();
        ingredients = new ArrayList<>();
    }

    public void addRecipe(String name, int prepTime, int cookTime, double servingSize, String description, String tags){
        ArrayList<Tag> splitTags = new ArrayList<Tag>();
        String[] tagsIndividual = tags.split(",");
        for (int i = 0; i < tagsIndividual.length; i++) {
            splitTags.add(new Tag(tagsIndividual[i]));
        }
        Recipe newRecipe = new Recipe(name, prepTime, cookTime, servingSize, ingredients, description, splitTags);
        repo.addRecipe(newRecipe);
    }

    public void ingredientUpdated(int index, String name, double amount , String measurement) {
        Ingredient newIngredient = new Ingredient(name, amount, measurement);
        ingredients.set(index, newIngredient);
    }
}
