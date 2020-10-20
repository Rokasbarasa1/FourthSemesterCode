package com.example.version2myrecipe.View;

public class Ingredient {
    private String name;
    private float quantity;
    private String unitOfMeassure;

    public Ingredient() {
        name = "";
        quantity = 0;
        unitOfMeassure = "";
    }

    public Ingredient(String name, float quantity, String unitOfMeassure) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeassure = unitOfMeassure;
    }

    public String getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnitOfMeassure() {
        return unitOfMeassure;
    }

    public String getAsString(){
        return name+" "+quantity+unitOfMeassure;
    }

    public String getEmpty(){
        return "";
    }
}
