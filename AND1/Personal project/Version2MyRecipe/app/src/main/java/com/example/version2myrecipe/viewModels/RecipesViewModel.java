package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;

import java.util.List;

public class RecipesViewModel extends ViewModel {
    private MutableLiveData<List<Recipe>> recipes;
}
