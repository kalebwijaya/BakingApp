package com.example.bakingapp.model;

public class Ingredient {
    private double quantity;
    private String measure;
    private String ingredient;

    public double getQuantity() { return quantity; }
    public void setQuantity(double value) { this.quantity = value; }

    public String getMeasure() { return measure; }
    public void setMeasure(String value) { this.measure = value; }

    public String getIngredient() { return ingredient; }
    public void setIngredient(String value) { this.ingredient = value; }
}
