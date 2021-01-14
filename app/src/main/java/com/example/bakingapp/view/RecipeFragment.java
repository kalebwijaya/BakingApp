package com.example.bakingapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.R;
import com.example.bakingapp.adapter.RecipeRecyclerViewAdapter;
import com.example.bakingapp.api.JsonHolder;
import androidx.lifecycle.ViewModelProvider;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.viewModels.RecipeViewModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeFragment extends Fragment {

    private View v;
    private RecyclerView rvRecipe;
    private RecipeRecyclerViewAdapter recyclerViewAdapter;
    private RecipeViewModel mRecipeViewModel;
    private List<Recipe> dataSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        mRecipeViewModel.init();

        dataSet = mRecipeViewModel.getRecipe().getValue();
        recyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(),dataSet);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {;

        v = inflater.inflate(R.layout.fragment_recipe, container, false);
        rvRecipe = v.findViewById(R.id.rv_recipe);
        rvRecipe.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRecipe.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecipeViewModel.getRecipe().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                saveData(recipes);
                dataSet.clear();
                dataSet.addAll(recipes);
                recyclerViewAdapter.notifyDataSetChanged();
                Log.d("API", "Data Changed");
            }
        });

    }

    private void saveData(List<Recipe> recipes){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipes);
        editor.putString("recipes", json);
        editor.apply();
        editor.commit();
        Log.d("Racipe","Saved To Shared");
    }

}