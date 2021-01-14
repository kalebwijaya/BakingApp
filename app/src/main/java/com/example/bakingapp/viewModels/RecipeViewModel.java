package com.example.bakingapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> mRecipe;
    private RecipeRepository mRepo;

    public void init(){
        if (mRecipe != null){
            return;
        }
        mRepo = RecipeRepository.getInstance();
        mRecipe = mRepo.getRecipe();
    }

    public MutableLiveData<List<Recipe>> getRecipe(){
        return mRecipe;
    }

}
