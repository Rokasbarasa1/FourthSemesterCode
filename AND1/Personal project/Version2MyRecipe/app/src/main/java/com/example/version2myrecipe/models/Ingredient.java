package com.example.version2myrecipe.models;

public class Ingredient {
    private String name;
    private double quantity;
    private String unitOfMeassure;

    public Ingredient() {
        name = "";
        quantity = 0;
        unitOfMeassure = "";
    }

    public Ingredient(String name, double quantity, String unitOfMeassure) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeassure = unitOfMeassure;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnitOfMeassure() {
        return unitOfMeassure;
    }

    public String getAsString(){
        return name+" "+quantity+ " " +unitOfMeassure;
    }

    public String getEmpty(){
        return "";
    }
}
