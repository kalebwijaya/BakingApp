package com.example.bakingapp.api;

import com.example.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonHolder {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getPosts();

}
