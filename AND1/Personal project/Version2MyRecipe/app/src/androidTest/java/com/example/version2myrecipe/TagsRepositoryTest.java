package com.example.version2myrecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.version2myrecipe.models.Ingredient;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TagsRepositoryTest {

    TagsRepository tagsRepository;

    @Test
    public void TestCreate(){
        tagsRepository = TagsRepository.getInstance();
    }

    @Before
    public void SetUp(){
        tagsRepository = TagsRepository.getInstance();
    }

    @Test
    public void TestAddRecipe(){
        Ingredient ingredient1 = new Ingredient("1", 1, "g");
        Ingredient ingredient2 = new Ingredient("2", 2, "g");
        Ingredient ingredient3 = new Ingredient("3", 3, "g");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientList.add(ingredient3);

        Tag tag1 = new Tag("Beef");
        Tag tag2 = new Tag("Chicken");
        Tag tag3 = new Tag("Lamb");
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag1);
        tagList.add(tag2);
        tagList.add(tag3);

        Recipe recipe = new Recipe("Name", 1, 1, 1, ingredientList, "Description", tagList);

        tagsRepository.addRecipe(recipe);
        assertEquals("Name",tagsRepository.getRecipe(0).getName());
    }

    @Test
    public void TestFindAddedRecipeTag(){
        Ingredient ingredient1 = new Ingredient("1", 1, "g");
        Ingredient ingredient2 = new Ingredient("2", 2, "g");
        Ingredient ingredient3 = new Ingredient("3", 3, "g");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientList.add(ingredient3);

        Tag tag1 = new Tag("Beef");
        Tag tag2 = new Tag("Chicken");
        Tag tag3 = new Tag("Lamb");
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag1);
        tagList.add(tag2);
        tagList.add(tag3);

        Recipe recipe = new Recipe("Name", 1, 1, 1, ingredientList, "Description", tagList);

        tagsRepository.addRecipe(recipe);

        assertEquals(3, recipe.getTags().size());
        assertEquals("Beef", recipe.getTags().get(0).getName());
        assertEquals("Chicken", recipe.getTags().get(1).getName());
        assertEquals("Lamb", recipe.getTags().get(2).getName());
    }

    @Test
    public void TestGetRecipeById(){
        Ingredient ingredient1 = new Ingredient("1", 1, "g");
        Ingredient ingredient2 = new Ingredient("2", 2, "g");
        Ingredient ingredient3 = new Ingredient("3", 3, "g");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientList.add(ingredient3);

        Tag tag1 = new Tag("Beef");
        Tag tag2 = new Tag("Chicken");
        Tag tag3 = new Tag("Lamb");
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag1);
        tagList.add(tag2);
        tagList.add(tag3);

        Recipe recipe = new Recipe("Name", 1, 1, 1, ingredientList, "Description", tagList);

        tagsRepository.addRecipe(recipe);
        MutableLiveData<List<Recipe>> list = tagsRepository.getRecipesByTag(tag2);
        assertEquals("Name", list.getValue().get(0).getName());
    }

}