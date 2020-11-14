package com.example.version2myrecipe.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsRepository {
    private static TagsRepository instance;
    private ArrayList<Tag> tagDataSet = new ArrayList<>();
    private ArrayList<Recipe> recipeDataSet = new ArrayList<>();


    public static TagsRepository getInstance(){
        if(instance == null){
            instance = new TagsRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Tag>> getTags(){
        if(tagDataSet.size() == 0){
            setTags();
        }
        MutableLiveData<List<Tag>> data = new MutableLiveData<>();
        data.setValue(tagDataSet);
        return data;
    }

    public Tag getTag(int index){
        return tagDataSet.get(index);
    }

    private void setTags(){
        tagDataSet.add(new Tag("Asian"));
        tagDataSet.add(new Tag("Catalan"));
        tagDataSet.add(new Tag("Spanish"));
        tagDataSet.add(new Tag("Savory"));
        tagDataSet.add(new Tag("Sweet"));
        tagDataSet.add(new Tag("Easy"));
        tagDataSet.add(new Tag("Complicated"));
        tagDataSet.add(new Tag("Chicken"));
        tagDataSet.add(new Tag("Beef"));
        tagDataSet.add(new Tag("Pork"));
        tagDataSet.add(new Tag("Complicated"));
    }

    public void addRecipe(Recipe newRecipe) {
        recipeDataSet.add(newRecipe);
        for (int i = 0; i < recipeDataSet.size(); i++) {
            for (int j = 0; j < recipeDataSet.get(i).getIngredients().size(); j++) {
                for (int k = 0; k < tagDataSet.size(); k++) {
                    if (!tagDataSet.get(k).equals(recipeDataSet.get(i).getTags().get(j))) {
                        tagDataSet.add(recipeDataSet.get(i).getTags().get(j));
                    }
                }
            }
        }
    }


    public MutableLiveData<List<Recipe>> getRecipes() {
        MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        data.setValue(recipeDataSet);
        return data;
    }
}
