package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import java.util.List;

public class TagsExpandedViewModel extends ViewModel {
    private MutableLiveData<List<Recipe>> recipes;
    private TagsRepository repo;

    public void init(){
        if(recipes != null){
            return;
        }
        repo = TagsRepository.getInstance();
        recipes = repo.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return recipes;
    }
}
