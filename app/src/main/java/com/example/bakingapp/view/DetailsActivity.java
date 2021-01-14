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

public class DetailsActivity extends AppCompatActivity {

    private int recipeID;
    private DetailsRecyclerViewAdapter recyclerViewAdapter;

    private Recipe recipe;
    private TextView tvServing;
    private RecyclerView rvDetails;
    private Button btnAddToFavorite;

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

        btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void getRecipe(){
        List<Recipe> tempData = new ArrayList<>();
        SharedPreferences sharedPreferences = this.getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("recipes",null);
        Type type = new TypeToken<List<Recipe>>() {}.getType();

        tempData = gson.fromJson(json, type);

        if (!tempData.isEmpty()){
            for (Recipe recipe : tempData){
                if (recipe.getID() == recipeID) {
                    this.recipe = recipe;
                    return;
                }
            }
        }
    }
}