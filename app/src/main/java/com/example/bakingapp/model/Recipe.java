package com.example.bakingapp.model;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private int servings;
    private String image;

    public int getID() { return id; }
    public void setID(int value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> value) { this.ingredients = value; }

    public List<Step> getSteps() { return steps; }
    public void setSteps(List<Step> value) { this.steps = value; }

    public int getServings() { return servings; }
    public void setServings(int value) { this.servings = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }
}

