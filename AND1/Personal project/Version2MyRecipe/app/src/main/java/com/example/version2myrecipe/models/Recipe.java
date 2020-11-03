package com.example.version2myrecipe.models;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int prepTime;
    private int cookTime;
    private int servingSize;
    private ArrayList<Ingredient> ingredients;
    private String description;
    private ArrayList<Tag> tags;

    public Recipe(String name, int prepTime, int cookTime, int servingSize, ArrayList<Ingredient> ingredients, String description, ArrayList<Tag> tags) {
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servingSize = servingSize;
        this.ingredients = ingredients;
        this.description = description;
        this.tags = tags;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }
}
