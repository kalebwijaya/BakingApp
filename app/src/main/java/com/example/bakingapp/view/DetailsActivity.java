package com.example.bakingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapter.DetailsRecyclerViewAdapter;
import com.example.bakingapp.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DetailsActivity extends AppCompatActivity {

    private int recipeID;
    private DetailsRecyclerViewAdapter recyclerViewAdapter;

    private Recipe recipe;
    private TextView tvServing;
    private RecyclerView rvDetails;
    private Button btnAddToFavorite;
    private List<Integer> favID = new ArrayList<>();
    private Boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvServing = findViewById(R.id.tv_details_serving);
        btnAddToFavorite = findViewById(R.id.btn_favorite);
        rvDetails = findViewById(R.id.rv_details);

        if(getIntent().hasExtra("recipeID")){
            recipeID = getIntent().getIntExtra("recipeID",0);
            getRecipe();
            Log.d("Details","Recipe Name = " + recipe.getName());
            tvServing.setText("Serving : "+recipe.getServings());
            getSupportActionBar().setTitle(recipe.getName());
        }

        if(recipe != null){
            recyclerViewAdapter = new DetailsRecyclerViewAdapter(recipe,this);
            rvDetails.setLayoutManager(new LinearLayoutManager(this));
            rvDetails.setAdapter(recyclerViewAdapter);
        }

        checkFav();
        if (isFav){
            btnAddToFavorite.setText("Remove Favorite");
        }

        btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFav){

                }else{
                    favID.add(recipeID);
                    saveFav(favID);
                }
            }
        });

    }

    private void getRecipe(){
        List<Recipe> tempData = new ArrayList<>();
        SharedPreferences sharedPreferences = this.getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String recipeJson = sharedPreferences.getString("recipes",null);
        String favIDJson = sharedPreferences.getString("favID",null);

        Type recipeType = new TypeToken<List<Recipe>>() {}.getType();
        Type favType = new TypeToken<List<Integer>>() {}.getType();

        tempData = gson.fromJson(recipeJson, recipeType);
        favID = gson.fromJson(favIDJson, favType);
        if(favID == null)
            favID = new ArrayList<>();

        if (!tempData.isEmpty()){
            for (Recipe recipe : tempData){
                if (recipe.getID() == recipeID) {
                    this.recipe = recipe;
                    return;
                }
            }
        }
    }

    private void saveFav(List<Integer> favID){
        SharedPreferences sharedPreferences = this.getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favID);
        editor.putString("favID", json);
        editor.apply();
        editor.commit();
        Log.d("Fav ID","Saved To Shared");
    }

    private void checkFav(){
        for (int id : favID){
            if(id == recipeID)
                isFav = true;
        }
    }

}