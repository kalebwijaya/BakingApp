package com.example.bakingapp.repositories;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bakingapp.api.JsonHolder;
import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRepository {

    private static RecipeRepository instance;
    private List<Recipe> recipes = new ArrayList<>();
    private MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

    public static RecipeRepository getInstance() {
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Recipe>> getRecipe(){
        getRecipeFromAPI();

        data.setValue(recipes);

        return data;
    }

    private void getRecipeFromAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder jsonHolder = retrofit.create(JsonHolder.class);

        Call<List<Recipe>> call = jsonHolder.getPosts();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(!response.isSuccessful()){
                    Log.d("API", "Code :" + response.code());
                    return;
                }
                recipes = response.body();
                data.setValue(recipes);
                Log.d("API", "Data Success");
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });

    }

}
