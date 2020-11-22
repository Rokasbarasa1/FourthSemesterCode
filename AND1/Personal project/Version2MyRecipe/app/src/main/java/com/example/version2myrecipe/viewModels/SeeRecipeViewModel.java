package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.repository.TagsRepository;

public class SeeRecipeViewModel extends ViewModel {
    private Recipe selectedRecipe;
    private TagsRepository repo;

    public void init(String recipeName){
        repo = TagsRepository.getInstance();
        selectedRecipe = repo.getRecipeByName(recipeName);
    }

    public Recipe getRecipe(){
        return selectedRecipe;
    }
}
